package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.Literally.SystemLiterally;
import org.example.domain.ResponseResult;
import org.example.domain.dto.AtricleDto;
import org.example.domain.enity.Category;
import org.example.domain.enity.SgArticle;
import org.example.domain.vo.ArticleDetailVo;
import org.example.domain.vo.ArticleListVo;
import org.example.domain.vo.HotArticleVo;
import org.example.domain.vo.PageVo;
import org.example.mapper.SgArticleMapper;
import org.example.service.CategoryService;
import org.example.service.SgArticleService;
import org.example.service.SgArticleTagService;
import org.example.utils.BeancopyUtils;
import org.example.utils.RedisCache;
import org.example.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 86147
 * @description 针对表【sg_article(文章表)】的数据库操作Service实现
 * @createDate 2024-01-14 11:56:18
 */
@Service
public class SgArticleServiceImpl extends ServiceImpl<SgArticleMapper, SgArticle> implements SgArticleService {

    @Override
    public ResponseResult hot() {
        LambdaQueryWrapper<SgArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SgArticle::getStatus, 0);//必须是正式文章
        wrapper.orderByDesc(SgArticle::getViewCount);//根据访问量进行升序排序
        Page<SgArticle> page = new Page<>(1, 10);//最多查询前10名
        page(page, wrapper);
        List<SgArticle> articles = page.getRecords();
        List<HotArticleVo> result = BeancopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(result);
    }


    @Autowired
    private CategoryService categoryService;


    @Override
    public ResponseResult articlelist(Integer pageNum, Integer pageSize, Long categoryid) {
//        1,wrapper设置
        LambdaQueryWrapper<SgArticle> wrapper = new LambdaQueryWrapper<>();
//       传入categoryid有效，且和文章的对应参数相同
        wrapper.eq(Objects.nonNull(categoryid) && categoryid > 0, SgArticle::getCategoryId, categoryid);
//        正式发布的文章
        wrapper.eq(SgArticle::getStatus, SystemLiterally.ARTICLE_y);
        wrapper.orderByDesc(SgArticle::getIsTop);


//        2，分页插件的使用
        Page<SgArticle> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        List<SgArticle> list = page.getRecords();//注意此处是一个获取属性值的操作，而不是数据库查询操作，


//        //第一种方式，用for循环遍历的方式
//        for (SgArticle article : list) {
//            //'article.getCategoryId()'表示从article表获取category_id字段，然后作为查询category表的name字段
//            Category category = categoryService.getById(article.getCategoryId());
//            //把查询出来的category表的name字段值，也就是article，设置给Article实体类的categoryName成员变量
//            article.setCategoryName(category.getName());
//
//        }

        list.stream().
                forEach(article -> {
                    article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                });


//      last:封装查询结果
        List<ArticleListVo> articleListVos = BeancopyUtils.copyBeanList(list, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());


        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getDetalis(Long id) {
        SgArticle Article=getById(id);
        ArticleDetailVo vo=BeancopyUtils.copyBean(Article,ArticleDetailVo.class);

//        设置分类名称
        Long categoryID= Article.getCategoryId();
        Category category=categoryService.getById(categoryID);
        if (category!=null){
            vo.setCategoryName(category.getName());
        }

        return ResponseResult.okResult(vo);
    }

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementViewCount("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }


    @Autowired
    private SgArticleTagService serviceTag_Article;


    @Override
    public void add(AtricleDto atricleDto) {

        SgArticle sgArticle= BeancopyUtils.copyBean(atricleDto, SgArticle.class);
        save(sgArticle);
        serviceTag_Article.insert_List(atricleDto.getId(),atricleDto.getTags());
    }
}




