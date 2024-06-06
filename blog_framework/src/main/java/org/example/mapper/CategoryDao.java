package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.Category;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-16 14:13:20
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}
