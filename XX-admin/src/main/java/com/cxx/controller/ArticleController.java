package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.AddArticleDto;
import com.cxx.domain.entity.Article;
import com.cxx.domain.vo.ArticleVo;
import com.cxx.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-13 10:03
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto articleDto){
        return articleService.add(articleDto);
    }

    @GetMapping("list")
    public ResponseResult list(Integer pageNum, Integer pageSize, Article article){
        return articleService.selectArticlePage(pageNum, pageSize, article);
    }

    @GetMapping("{id}")
    public ResponseResult getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id);
    }

    @PutMapping
    public ResponseResult edit(@RequestBody ArticleVo articleVo){
        return articleService.edit(articleVo);
    }

    @DeleteMapping("{id}")
    public ResponseResult delete(@PathVariable Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }
}
