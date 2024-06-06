package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.Comment;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-20 12:47:57
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
