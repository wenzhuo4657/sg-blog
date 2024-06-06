package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.domain.ResponseResult;
import org.example.domain.enity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-01-17 14:37:55
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
