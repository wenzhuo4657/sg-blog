package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.ResponseResult;
import org.example.domain.enity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-01-20 12:46:49
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String number, Long article, Integer pageNum, Integer pageSize);

    ResponseResult add(Comment comment);
}
