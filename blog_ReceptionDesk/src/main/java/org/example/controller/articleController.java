package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.domain.ResponseResult;
import org.example.domain.enity.SgArticle;
import org.example.service.impl.SgArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/article")
@Api(tags = "文章Controller",value = "在这里")
public class articleController {

    @Autowired
    private SgArticleServiceImpl sgArticleService;


    @GetMapping(value = "/01service")
    public List<SgArticle> ser1(){
        return sgArticleService.list();
    }


    @GetMapping(value = "/hotArticleList")
    @ApiOperation(value = "获取所有热门文章", notes = "----end")
    public ResponseResult hotArticle(){
        ResponseResult result=sgArticleService.hot();
        return result;
    }


//    查询指定分类、页大小、页下标的页中的所有文章
    @GetMapping(value = "/articleList")
    @ApiOperation(value = "获取指定文章", notes = "----end")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页数"),
            @ApiImplicitParam(name = "pageSize",value = "每页的大小"),
            @ApiImplicitParam(name = "category",value = "所属分类" )

    }
                )
    public  ResponseResult  articleList(Integer pageNum,Integer pageSize,Long category){
            return  sgArticleService.articlelist(pageNum,pageSize,category);
    }


    @GetMapping(value = "/{id}")
    public ResponseResult getDetails(@PathVariable("id")  Long id){
        return  sgArticleService.getDetalis(id);
    }


    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
       return sgArticleService.updateViewCount(id);
    }
}
