package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.domain.ResponseResult;
import org.example.domain.dto.EditTagDto;
import org.example.domain.dto.TagDto;
import org.example.domain.enity.Tag;
import org.example.domain.vo.PageVo;
import org.example.domain.vo.TagVo;
import org.example.mapper.TagMapper;
import org.example.service.TagService;
import org.example.utils.BeancopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-02-18 18:10:16
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> Select(Integer pageSize, Integer pageNum, TagDto tagDto) {
        LambdaQueryWrapper<Tag> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(tagDto.getName()), Tag::getName,tagDto.getName());
        wrapper.eq(StringUtils.hasText(tagDto.getRemark()),Tag::getRemark,tagDto.getRemark());
        Page<Tag> page=new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        PageVo pageVo=new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult Insert(TagDto tagDto) {
        TagMapper baseMapper1 = getBaseMapper();
        Tag tag = BeancopyUtils.copyBean(tagDto, Tag.class);
        baseMapper1.insert(tag);
        return ResponseResult.okResult();
    }

    @Override
    public void delete(Integer id) {
        LambdaQueryWrapper<Tag> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getId,id);

        getBaseMapper().delete(wrapper);

    }

    @Override
    public void updateDto(EditTagDto dto) {

        updateById(BeancopyUtils.copyBean(dto,Tag.class));
    }


    @Override
    public List<TagVo> listAlltag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeancopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;

    }
}
