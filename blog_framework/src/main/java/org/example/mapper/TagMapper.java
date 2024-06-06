package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.Tag;


/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-18 18:10:14
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
