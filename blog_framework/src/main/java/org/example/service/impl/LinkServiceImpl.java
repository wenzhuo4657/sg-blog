package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.Literally.SystemLiterally;
import org.example.domain.ResponseResult;
import org.example.domain.enity.Link;
import org.example.domain.vo.LinkVo;
import org.example.mapper.LinkDao;
import org.example.service.LinkService;
import org.example.utils.BeancopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-01-17 14:37:55
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkDao, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemLiterally.LInk_s);
        List<Link> lins=list(wrapper);
        List<LinkVo> list1= BeancopyUtils.copyBeanList(lins, LinkVo.class);
        return ResponseResult.okResult(list1);
    }
}
