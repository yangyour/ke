package cn.dblearn.blog.portal.operation.service;

import cn.dblearn.blog.entity.operation.Tag;
import cn.dblearn.blog.entity.operation.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * TagService
 *
 * @description
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取tagVoList
     * @return
     */
    List<TagVO> listTagVo();
}
