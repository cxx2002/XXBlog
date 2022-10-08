package com.cxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxx.domain.ResponseResult;
import com.cxx.domain.entity.Comment;

/**
* @author 陈喜喜
* @description 针对表【xx_comment(评论表)】的数据库操作Service
* @createDate 2022-10-04 10:00:43
*/
public interface CommentService extends IService<Comment> {


    ResponseResult addComment(Comment comment);

    ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);
}
