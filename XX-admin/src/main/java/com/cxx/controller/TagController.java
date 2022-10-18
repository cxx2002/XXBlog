package com.cxx.controller;

import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.TagListDto;
import com.cxx.domain.entity.Tag;
import com.cxx.domain.vo.TagVo;
import com.cxx.service.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-10-10 16:05
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @GetMapping("listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list=tagService.listAllTag();
        return ResponseResult.okResult(list);
    }

    @PostMapping
    public ResponseResult add(@RequestBody TagListDto tagListDto) {
        return tagService.add(tagListDto);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteById(@PathVariable Long id) {
        return tagService.deleteById(id);
    }

    @GetMapping("{id}")
    public ResponseResult getOne(@PathVariable Long id) {
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    public ResponseResult update(@RequestBody Tag tag){
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }
}
