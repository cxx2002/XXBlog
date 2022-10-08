package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-02 15:16
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("getCategoryList")
    public ResponseResult getCategoryList() {
       return categoryService.getCategoryList();
    }

}
