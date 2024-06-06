package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.Link;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-17 14:39:32
 */
@Mapper
public interface LinkDao extends BaseMapper<Link> {

}
