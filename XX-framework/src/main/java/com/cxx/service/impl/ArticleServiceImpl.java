package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Article;
import com.cxx.domain.entity.Category;
import com.cxx.domain.vo.ArticleDetailVo;
import com.cxx.domain.vo.ArticleListVo;
import com.cxx.domain.vo.HotArticleVo;
import com.cxx.domain.vo.PageVo;
import com.cxx.mapper.ArticleMapper;
import com.cxx.service.ArticleService;
import com.cxx.service.CategoryService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.RedisCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 陈喜喜
 * @date 2022-10-01 16:04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private CategoryService categoryService;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResponseResult<Article> hotArticleList() {
        //必须是正式文章  安装浏览量降序  最多只能查10条
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL).orderByAsc(Article::getViewCount);
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
        articles.forEach(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()));
        //articles = articles.stream().map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())).collect(Collectors.toList())
        List<ArticleListVo> rowVo = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
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
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis对应文章id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(),1);
        return ResponseResult.okResult();
    }
}
