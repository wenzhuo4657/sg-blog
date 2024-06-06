package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.domain.enity.SgArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 86147
* @description 针对表【sg_article(文章表)】的数据库操作Mapper
* @createDate 2024-01-14 11:56:18
* @Entity domain.domain.SgArticle
*/

@Mapper
public interface SgArticleMapper extends BaseMapper<SgArticle> {

}




