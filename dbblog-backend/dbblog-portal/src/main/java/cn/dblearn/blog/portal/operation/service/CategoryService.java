package cn.dblearn.blog.portal.operation.service;

import cn.dblearn.blog.entity.operation.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * CategoryService
 *
 * @description
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取categoryList
     * @param params
     * @return
     */
    List<Category> listCategory(Map<String, Object> params);
}
