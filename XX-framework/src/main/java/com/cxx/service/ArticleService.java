package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.AddArticleDto;
import com.cxx.domain.entity.Article;
import com.cxx.domain.vo.ArticleVo;

/**
 * @author 陈喜喜
 * @date 2022-10-01 16:03
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Integer id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto articleDto);

    ResponseResult selectArticlePage(Integer pageNum, Integer pageSize, Article article);

    ResponseResult getArticleById(Long id);

    ResponseResult edit(ArticleVo articleVo);

    ResponseResult newArticleList();

}
