package com.cxx.controller;

import com.cxx.constants.SystemConstants;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Comment;
import com.cxx.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 陈喜喜
 * @date 2022-10-04 10:39
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("commentList")
    public ResponseResult getCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return commentService.getCommentList(SystemConstants.LINK_COMMENT,null,pageNum, pageSize);
    }
}
