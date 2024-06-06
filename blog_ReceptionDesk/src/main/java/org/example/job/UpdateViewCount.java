package org.example.job;


import org.example.annotation.SystemLog;
import org.example.domain.enity.SgArticle;
import org.example.service.SgArticleService;
import org.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCount {


    @Autowired
    private RedisCache  redisCache;

    @Autowired
    private SgArticleService sgArticleService;

    @Scheduled(cron = "0/55 * * * * ?")
    public  void updateViewCount(){

//        从redis中查询访问量并转换为Sgarticle
        Map<String,Integer> map=redisCache.getCacheMap("article:viewCount");
        List<SgArticle> list=map.entrySet()
                .stream().
                map(entry -> new SgArticle(Long.valueOf(entry.getKey()),entry.getValue().longValue())).
                collect(Collectors.toList());


//        更新数据库
        sgArticleService.updateBatchById(list);


    }
}
