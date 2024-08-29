package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.Literally.SystemLiterally;
import org.example.domain.ResponseResult;
import org.example.domain.dto.CategoryDto;
import org.example.domain.enity.Category;
import org.example.domain.enity.SgArticle;
import org.example.domain.vo.CategoryVo;
import org.example.mapper.CategoryDao;
import org.example.mapper.SgArticleMapper;
import org.example.service.CategoryService;
import org.example.utils.BeancopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-01-16 14:10:20
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {


    @Autowired
    private  SgArticleMapper sgArticleMapper;
    @Override
    public ResponseResult getlist() {
//        mp查询所有可发布文章
        LambdaQueryWrapper<SgArticle> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SgArticle::getStatus, SystemLiterally.ARTICLE_y);
        List<SgArticle> con= sgArticleMapper.selectList(wrapper);

//        Stream流得到所有 有 可发布文章 的分类
        Set<Long>  CategoryId=con.stream()
                .map(new Function<SgArticle, Long>() {
                    @Override
                    public Long apply(SgArticle o) {
                        return o.getCategoryId();
                    }
                })
                .collect(Collectors.toSet());


//        查询所有分类
        List<Category> list=listByIds(CategoryId);
//       过滤掉不可发布的分类
        list=list.stream()
                .filter(o->o.getStatus().equals(SystemLiterally.STRING_y))
                .collect(Collectors.toList());


//        分装为vo
        List<CategoryVo> li= BeancopyUtils.copyBeanList(list,CategoryVo.class);

        return ResponseResult.okResult(li);
    }

    @Override
    public List<CategoryDto> getlist1() {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus,"0");
        List<Category> list = list(wrapper);
        List<CategoryDto> categoryVos = BeancopyUtils.copyBeanList(list, CategoryDto.class);
        return categoryVos;
    }
}
