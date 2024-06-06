package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.enity.SgArticleTag;
import org.example.mapper.SgArticleTagMapper;
import org.example.service.SgArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章标签关联表(SgArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2024-02-26 09:40:25
 */
@Service("sgArticleTagService")
public class SgArticleTagServiceImpl extends ServiceImpl<SgArticleTagMapper, SgArticleTag> implements SgArticleTagService {


    @Override
    public void insert_List(Long ArticleId, List<Long> tags) {
        tags.stream()
                .forEach(tagsid ->save(new SgArticleTag(ArticleId,tagsid)));
    }
}
