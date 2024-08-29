package org.example.controller;

import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.dto.CommentDto;
import org.example.domain.enity.Comment;
import org.example.service.CommentService;
import org.example.service.impl.CommentServiceImpl;
import org.example.utils.BeancopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService  commentService;


    @GetMapping("/commentList")
    public ResponseResult  commentList(Long articleId,Integer pageNum,Integer pageSize){
        return  commentService.commentList(AppHttpCodeEnum.Article_comment,articleId,pageNum,pageSize);
    }


    @PostMapping
    public  ResponseResult addComment(@RequestBody CommentDto commentDto){
        Comment comment= BeancopyUtils.copyBean(commentDto, Comment.class);
        return  commentService.add(comment);
    }


    @GetMapping("linkCommentList")
    public ResponseResult LinkCommentList(Integer pageNum,Integer pageSize){
        return  commentService.commentList(AppHttpCodeEnum.Link_Comment,null,pageNum,pageSize);
    }





}
