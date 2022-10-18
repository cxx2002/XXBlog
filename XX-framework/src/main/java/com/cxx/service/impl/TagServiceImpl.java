package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.TagListDto;
import com.cxx.domain.entity.Tag;
import com.cxx.domain.vo.PageVo;
import com.cxx.domain.vo.TagVo;
import com.cxx.mapper.TagMapper;
import com.cxx.service.TagService;
import com.cxx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author 陈喜喜
* @description 针对表【xx_tag(标签)】的数据库操作Service实现
* @createDate 2022-10-10 16:02:48
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());
        Page<Tag> page = new Page<>(pageNum, pageSize);
        super.page(page, queryWrapper);
        //封装数据返回
        PageVo vo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult add(TagListDto tagListDto) {
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        this.save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteById(Long id) {
        this.removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> list = super.list(queryWrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }
}




