package com.cxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Comment;
import com.cxx.domain.entity.User;
import com.cxx.domain.vo.CommentVo;
import com.cxx.domain.vo.PageVo;
import com.cxx.enums.AppHttpCodeEnum;
import com.cxx.exception.SystemException;
import com.cxx.mapper.CommentMapper;
import com.cxx.service.CommentService;
import com.cxx.service.UserService;
import com.cxx.utils.BeanCopyUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈喜喜
 * @description 针对表【xx_comment(评论表)】的数据库操作Service实现
 * @createDate 2022-10-04 10:00:43
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Resource
    private UserService userService;

    @Override
    public ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询articleID对应的文章的根评论（rootId=-1） 分页查询
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        //评论类型
        queryWrapper.eq(Comment::getType, commentType);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        super.page(page, queryWrapper);
        List<CommentVo> vos = this.toCommentVoList(page.getRecords());
        //再查子评论
        vos = this.getChildren(vos);
        return ResponseResult.okResult(new PageVo(vos, page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (StringUtils.isBlank(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        super.save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        //遍历vo列表    【A】回复【B】     通过creatBy查询用户的昵称（写该评论的人）【A】
        // 通过commentUserId查询回复评论的人的昵称（并且rootId！=-1），是直接回复文章的评论（根评论）【B】
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        commentVos = commentVos.stream().map(commentVo -> {
            User byId = userService.getById(commentVo.getCreateBy());
            commentVo.setUsername(byId.getNickName());
            if (commentVo.getRootId() != -1) {
                User toCommentUser = userService.getById(commentVo.getToCommentUserId());
                commentVo.setToCommentUserName(toCommentUser.getNickName());
            }
            return commentVo;
        }).collect(Collectors.toList());

        return commentVos;
    }

    private List<CommentVo> getChildren(List<CommentVo> vos) {
        //用该评论的id查询所有子评论
        vos = vos.stream().peek(vo -> {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getRootId, vo.getId());
            queryWrapper.orderByAsc(Comment::getCreateTime);
            List<Comment> childrenList = super.list(queryWrapper);
            List<CommentVo> childrenListVos = this.toCommentVoList(childrenList);
            vo.setChildren(childrenListVos);
        }).collect(Collectors.toList());
        return vos;
    }
}




