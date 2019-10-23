package cn.dblearn.blog.portal.operation.service;

import cn.dblearn.blog.entity.operation.Link;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * LinkService
 *

 * @description
 */
public interface LinkService extends IService<Link> {

    /**
     * 获取link列表
     * @return
     */
    List<Link> listLink();
}
