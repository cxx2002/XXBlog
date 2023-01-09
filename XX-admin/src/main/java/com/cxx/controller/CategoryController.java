package com.cxx.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Category;
import com.cxx.domain.vo.CategoryVo;
import com.cxx.domain.vo.ExcelCategoryVo;
import com.cxx.domain.vo.PageVo;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.service.CategoryService;
import com.cxx.utils.BeanCopyUtils;
import com.cxx.utils.WebUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-10-13 9:30
 */
@RestController
@RequestMapping("content/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    //导出excel   Security框架权限验证
    @PreAuthorize("@ps.hasPermission('content:category:export')")  // 这里用自己写的权限匹配方法
    //嘻嘻博客权限是直接丢给前端vue组件路由来实现权限控制（直接不展示相应的前端组件）
    //但是可以通过postman，用一个普通用户登录拿到服务端返回的token，然后放在请求头里就直接绕过了前端直接拿到数据以及修改数据
    //这里表示没有content:category:export权限信息就不能执行这个api
    //在每个api上弄上这个注解写上对应的权限就可以防止上面事情发生
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryList = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryList, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @PutMapping
    public ResponseResult edit(@RequestBody Category category){
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult remove(@PathVariable(value = "id")Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Category category = categoryService.getById(id);
        return ResponseResult.okResult(category);
    }
    @PostMapping
    public ResponseResult add(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.okResult();
    }
    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public ResponseResult list(Category category, Integer pageNum, Integer pageSize) {
        PageVo pageVo = categoryService.selectCategoryPage(category,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }


    @GetMapping("listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> listVo = categoryService.listAllCategory();
        return ResponseResult.okResult(listVo);
    }
}
