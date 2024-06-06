package org.example.service;

import org.example.domain.ResponseResult;
import org.example.domain.dto.AtricleDto;
import org.example.domain.enity.SgArticle;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86147
* @description 针对表【sg_article(文章表)】的数据库操作Service
* @createDate 2024-01-14 11:56:18
*/
public interface SgArticleService extends IService<SgArticle> {

    ResponseResult hot();

    ResponseResult articlelist(Integer pageNum, Integer pagetSize, Long category);

    ResponseResult getDetalis(Long id);

    ResponseResult updateViewCount(Long id);

    void add(AtricleDto atricleDto);
}
