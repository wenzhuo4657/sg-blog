package org.example.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import org.example.annotation.SystemLog;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.enity.Category;
import org.example.domain.vo.CategoryVo;
import org.example.domain.vo.ExcelCategoryVo;
import org.example.service.CategoryService;
import org.example.utils.BeancopyUtils;
import org.example.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private   CategoryService  categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult getAllCategory(){

        return ResponseResult.okResult(categoryService.getlist1());
    }

    @GetMapping("/export")
    public  void  export(HttpServletResponse response){


        try{
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            List<Category> LIST=categoryService.list();
            List<CategoryVo> vo= BeancopyUtils.copyBeanList(LIST, CategoryVo.class);
            EasyExcel.
                    write(response.getOutputStream(), ExcelCategoryVo.class)
                    .autoCloseStream(Boolean.FALSE).sheet("文章分类")
                    .doWrite(vo);


        } catch (Exception e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
            throw new RuntimeException(e);
        }

    }

}
