package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.ResponseResult;
import org.example.domain.dto.CategoryDto;
import org.example.domain.enity.Category;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-01-16 14:10:19
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getlist();

    List<CategoryDto> getlist1();
}
