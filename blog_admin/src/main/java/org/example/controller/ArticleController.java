package org.example.controller;


import org.example.domain.ResponseResult;
import org.example.domain.dto.AtricleDto;
import org.example.service.SgArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {



    @Autowired
    private SgArticleService sgArticleService;
    @PostMapping
    public ResponseResult putAtrcle(@RequestBody  AtricleDto atricleDto){
        sgArticleService.add(atricleDto);
        return ResponseResult.okResult();
    }

}
