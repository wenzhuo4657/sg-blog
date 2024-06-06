package org.example.controller;

import org.example.annotation.SystemLog;
import org.example.domain.ResponseResult;
import org.example.domain.dto.EditTagDto;
import org.example.domain.dto.TagDto;
import org.example.domain.enity.Tag;
import org.example.domain.vo.PageVo;
import org.example.domain.vo.TagVo;
import org.example.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/content/tag")
public class tagController {


    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageSize, Integer pageNum, TagDto tagDto){
        return tagService.Select(pageSize,pageNum,tagDto);
    }

    @PostMapping
    @SystemLog
    public ResponseResult Insert(@RequestBody TagDto tagDto){
        return tagService.Insert(tagDto);
    }


    @DeleteMapping("/{id}")
    @SystemLog
    public  ResponseResult delete(@PathVariable Integer id){
        if (Objects.isNull(id)){
            return  ResponseResult.errorResult(500,"没有输入");
        }

        tagService.delete(id);
        return  ResponseResult.okResult();
    }



    @GetMapping("/{id}")
    public  ResponseResult  getInfo(@PathVariable(value = "id") Long id){

        Tag tag=tagService.getById(id);
        return  ResponseResult.okResult(tag);
    }


    @PutMapping
    public  ResponseResult update(@RequestBody EditTagDto dto){
        tagService.updateDto(dto);
        return ResponseResult.okResult();
    }


    @GetMapping("/listAllTag")
    public  ResponseResult listAlltag(){
        List<TagVo> LIST=tagService.listAlltag();
        return ResponseResult.okResult(LIST);
    }
}
