package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Link;
import com.cxx.domain.vo.PageVo;

/**
* @author 陈喜喜
* @description 针对表【xx_link(友链)】的数据库操作Service
* @createDate 2022-10-02 18:45:12
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
