package org.example.runner;

import org.example.annotation.SystemLog;
import org.example.domain.enity.SgArticle;
import org.example.mapper.SgArticleMapper;
import org.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private SgArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;


    @Override
    public void run(String... args) throws Exception {
//        获取浏览量集合
        List<SgArticle> list = articleMapper.selectList(null);
        Map<String,Integer> ViewCountMap=list.stream().collect(Collectors.toMap(
                sgArticle -> sgArticle.getId().toString()
                , sgArticle -> sgArticle.getViewCount().intValue()));
//        存入redis中
        redisCache.setCacheMap("article:viewCount",ViewCountMap);


    }
}