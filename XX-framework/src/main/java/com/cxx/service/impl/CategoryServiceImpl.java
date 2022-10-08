package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Article;
import com.cxx.domain.entity.Category;
import com.cxx.domain.vo.CategoryVo;
import com.cxx.mapper.CategoryMapper;
import com.cxx.service.ArticleService;
import com.cxx.service.CategoryService;
import com.cxx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author 陈喜喜
 * @since 2022-10-01 23:41:30
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表（状态为已发布的文章）  获取文章的id（并且去重）  根据id查询分类表  封装vo
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        Collection<Article> articleList = articleService.list(queryWrapper);
        Set<Long> categoryIds = articleList.stream().
                map(Article::getCategoryId).
                collect(Collectors.toSet());
        List<Category> categories = super.listByIds(categoryIds);
        categories= categories.stream().
                filter(category -> category.getStatus().equals(SystemConstants.STATUS_NORMAL)).
                collect(Collectors.toList());
        List<CategoryVo> vo = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(vo);
    }
}

