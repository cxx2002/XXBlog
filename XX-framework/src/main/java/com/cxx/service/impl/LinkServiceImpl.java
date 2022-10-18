package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Link;
import com.cxx.domain.vo.LinkVo;
import com.cxx.domain.vo.PageVo;
import com.cxx.mapper.LinkMapper;
import com.cxx.service.LinkService;
import com.cxx.utils.BeanCopyUtils;
import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈喜喜
 * @description 针对表【xx_link(友链)】的数据库操作Service实现
 * @createDate 2022-10-02 18:45:12
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = super.list(queryWrapper);
        List<LinkVo> vos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(vos);
    }

    @Override
    public PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        linkLambdaQueryWrapper.eq(Strings.hasText(link.getName()), Link::getName, link.getName());
        linkLambdaQueryWrapper.eq(Strings.hasText(link.getStatus()), Link::getStatus, link.getStatus());
        super.page(page, linkLambdaQueryWrapper);
        return new PageVo(page.getRecords(), page.getTotal());
    }
}




