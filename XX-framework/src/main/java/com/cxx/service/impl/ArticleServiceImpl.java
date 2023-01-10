package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.AddArticleDto;
import com.cxx.domain.entity.Article;
import com.cxx.domain.entity.ArticleTag;
import com.cxx.domain.entity.Category;
import com.cxx.domain.entity.User;
import com.cxx.domain.vo.*;
import com.cxx.mapper.ArticleMapper;
import com.cxx.service.ArticleService;
import com.cxx.service.ArticleTagService;
import com.cxx.service.CategoryService;
import com.cxx.service.UserService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.RedisCache;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 陈喜喜
 * @date 2022-10-01 16:04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private CategoryService categoryService;
    @Resource
    private UserService userService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult<Article> hotArticleList() {
        //必须是正式文章  安装浏览量降序  最多只能查10条
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getViewCount);
        Page page = super.page(new Page(1, 10), queryWrapper);
        List<Article> articles = page.getRecords();
        //BeanUtils.copyProperties()浅拷贝给vo
        //List<HotArticleVo> articleVos = new ArrayList<>();
        // for (Article article : articles) {
        //     HotArticleVo vo = new HotArticleVo();
        //     BeanUtils.copyProperties(article, vo);
        //     articleVos.add(vo);
        // }
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    @Override
    //新添功能（显示作者）空指针异常解决（注释掉77行）
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //categoryId非空的eq条件  状态是正式发布的  对isTop降序排序（置顶） 分页查询 查询分类名称 封装成vo
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getIsTop).orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(pageNum, pageSize);
        super.page(page, queryWrapper);
        //这里articles和page.getRecords()存的是同一个实例，并不是articles重新开辟空间创建了一个新的实例
        List<Article> articles = page.getRecords();
        //获取文章分类名和作者  这里要注意article.getCreateBy()如果自己直接在mysql添加的文章没有create_by就会报空指针
        articles.forEach(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()));
        articles.forEach(article -> article.setUserName(userService.getById(article.getCreateBy()).getNickName()));
        /*articles = articles.stream().map(article -> {
                article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                return article;
                }).collect(Collectors.toList());*/
        List<ArticleListVo> rowVo = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(rowVo, page.getTotal());
        //前端要的响应结果是rows（文章信息列表）和total（总记录数），两者是并列的
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Integer id) {
        //根据id查询文章信息  封装成vo 根据分类id查询分类名称  再为vo setCategoryName封装返回
        Article article = super.getById(id);
        //从redis获取最新浏览量
        Integer viewCount = redisCache.getCacheMapValue(("article:viewCount"), id.toString());
        article.setViewCount(viewCount.longValue());
        ArticleDetailVo vo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryService.getById(vo.getCategoryId());
        if (category != null) {
            vo.setCategoryName(category.getName());
        }
        //根据文章的createBy获取文章作者昵称
        User author = userService.getById(article.getCreateBy());
        if (author != null) {
            vo.setUserName(author.getNickName());
        }
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis对应文章id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(),1);//加1
        return ResponseResult.okResult();
    }

    @Override
    @Transactional //添加事务保证原子性
    public ResponseResult add(AddArticleDto articleDto) {
        //添加博客文章
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        super.save(article);
        //获取ArticleTag实体列表（1对多）
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        //添加博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return null;
    }

    @Override
    public ResponseResult selectArticlePage(Integer pageNum, Integer pageSize, Article article) {
        //模糊查询文章标题和摘要
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.hasText(article.getTitle()), Article::getTitle, article.getTitle());
        queryWrapper.like(Strings.hasText(article.getSummary()), Article::getSummary, article.getSummary());
        super.page(page, queryWrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getArticleById(Long id) {
        //先获取文章信息 再获取关联标签列表 最后封装数据返回
        Article article = super.getById(id);
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, article.getId());
        //获取文章和标签的一对多对应列表
        List<ArticleTag> articleTagList = articleTagService.list(queryWrapper);
        //获取标签列表
        List<Long> tagList = articleTagList.stream()
                .map(ArticleTag::getTagId).collect(Collectors.toList());
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        articleVo.setTags(tagList);
        return ResponseResult.okResult(articleVo);
    }

    @Override
    public ResponseResult newArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByDesc(Article::getCreateTime);
        Page page = super.page(new Page(1, 3), queryWrapper);
        List<Article> articles = page.getRecords();
        List<NewArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, NewArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult edit(ArticleVo articleVo) {
        Article article = BeanCopyUtils.copyBean(articleVo, Article.class);
        //更新该文章的信息
        super.updateById(article);
        //删除原有的标签和文章的关联
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, article.getId());
        articleTagService.remove(queryWrapper);
        //添加新的标签和文章的关联
        List<ArticleTag> articleTagList = articleVo.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTagList);
        return ResponseResult.okResult();
    }
}
