package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.SgArticleTag;


/**
 * 文章标签关联表(SgArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-26 09:40:21
 */
@Mapper
public interface SgArticleTagMapper extends BaseMapper<SgArticleTag> {

}
