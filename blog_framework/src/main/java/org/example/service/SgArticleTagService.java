package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.enity.SgArticleTag;

import java.util.List;


/**
 * 文章标签关联表(SgArticleTag)表服务接口
 *
 * @author makejava
 * @since 2024-02-26 09:40:24
 */
public interface SgArticleTagService extends IService<SgArticleTag> {

    void insert_List(Long ArticleId, List<Long> tags);
}
