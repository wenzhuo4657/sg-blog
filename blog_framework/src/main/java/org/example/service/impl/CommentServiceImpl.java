package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.enity.Comment;
import org.example.domain.vo.CommentVo;
import org.example.domain.vo.PageVo;
import org.example.exception.SystemException;
import org.example.mapper.CommentMapper;
import org.example.service.CommentService;
import org.example.utils.BeancopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-01-20 12:46:49
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Override
    public ResponseResult commentList(String number, Long article, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AppHttpCodeEnum.Article_comment.equals(number),Comment::getArticleId,article);
        wrapper.eq(Comment::getRootId,-1);
        wrapper.eq(Comment::getType,number);

        Page<Comment> page=new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<CommentVo>  list=xxVo(page.getRecords());


        for (CommentVo vo: list){
            List <CommentVo> children=getChildren(vo);
            vo.setChildren(children);

        }

        return ResponseResult.okResult(new PageVo(list, page.getTotal()));
    }

    private List<CommentVo> getChildren(CommentVo vo) {
        LambdaQueryWrapper<Comment> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getRootId,vo.getId());
        wrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> comments=list(wrapper);
        List<CommentVo>  C=xxVo(comments);
        return  C;

    }

    @Autowired
    private  UserServiceImpl service;


//    处理CommentVo中不匹配字段
    private List<CommentVo> xxVo(List<Comment> records) {
        List<CommentVo> listVo= BeancopyUtils.copyBeanList(records,CommentVo.class);
        for (CommentVo vo:listVo){
            String nickname=service.getById(vo.getCreateBy()).getNickName();
            vo.setUsername(nickname);
            if(vo.getRootId()!=-1){
                String ToCommentUserName = service.getById(vo.getToCommentUserId()).getNickName();
                vo.setToCommentUserName(ToCommentUserName);
            }
        }
        return  listVo;
    }

    @Override
    public ResponseResult add(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }
}
