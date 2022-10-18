package com.cxx.controller;

import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.dto.AddCommentDto;
import com.cxx.domain.entity.Comment;
import com.cxx.service.CommentService;
import com.cxx.utils.BeanCopyUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-04 10:39
 */
@RestController
@RequestMapping("comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("commentList")
    public ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    @GetMapping("linkCommentList")
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value="页号"),
            @ApiImplicitParam(name = "pageSize",value="每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.getCommentList(SystemConstants.LINK_COMMENT,null,pageNum, pageSize);
    }
}
