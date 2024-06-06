package org.example.controller;


import org.example.domain.ResponseResult;
import org.example.service.CategoryService;
import org.example.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService  category;

    @GetMapping(value = "/getCategoryList")
    public ResponseResult Service_01(){
        return  category.getlist();
    }


}
