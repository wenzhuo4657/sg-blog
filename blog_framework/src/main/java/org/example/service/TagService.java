package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.ResponseResult;
import org.example.domain.dto.EditTagDto;
import org.example.domain.dto.TagDto;
import org.example.domain.enity.Tag;
import org.example.domain.vo.PageVo;
import org.example.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2024-02-18 18:10:16
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> Select(Integer pageSize, Integer pageNum, TagDto tagDto);

    ResponseResult Insert(TagDto tagDto);

    void delete(Integer id);

    void updateDto(EditTagDto dto);

    List<TagVo> listAlltag();
}
