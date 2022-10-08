package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Article;

/**
 * @author 陈喜喜
 * @date 2022-10-01 16:03
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Integer id);

    ResponseResult updateViewCount(Long id);

}
