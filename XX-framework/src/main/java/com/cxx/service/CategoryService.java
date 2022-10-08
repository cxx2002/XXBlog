package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author 陈喜喜
 * @since 2022-10-01 23:41:27
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

