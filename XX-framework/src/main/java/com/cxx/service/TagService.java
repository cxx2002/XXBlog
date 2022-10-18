package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.TagListDto;
import com.cxx.domain.entity.Tag;
import com.cxx.domain.vo.TagVo;

import java.util.List;

/**
* @author 陈喜喜
* @description 针对表【xx_tag(标签)】的数据库操作Service
* @createDate 2022-10-10 16:02:48
*/
public interface TagService extends IService<Tag> {

    ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult add(TagListDto tagListDto);

    ResponseResult deleteById(Long id);

    List<TagVo> listAllTag();

}
