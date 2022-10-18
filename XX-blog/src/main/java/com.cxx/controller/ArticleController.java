package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Article;
import com.cxx.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-10-01 15:12
 */
@RestController
@RequestMapping("article")
public  class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("hotArticleList")
    public ResponseResult hotArticleList() {
        //查询热门文章十篇，封装成ResponseResult返回
        return articleService.hotArticleList();
    }
    @GetMapping("newArticleList")
    public ResponseResult newArticleList() {
        //查询最新文章三篇，封装成ResponseResult返回
        return articleService.newArticleList();
    }

    @GetMapping("articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId) {
        //如果是restful风格，则参数要加@PathValue("pageNum")...，或者.../{pageNum}/{}/{}
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Integer id){
        return articleService.getArticleDetail(id);
    }

    @PutMapping("updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable Long id){
        return articleService.updateViewCount(id);
    }






    @GetMapping("list")
    public List<Article> getArticleList() {
        return articleService.list();
    }

}
