package cn.dblearn.blog.portal.operation.service.impl;

import cn.dblearn.blog.entity.operation.Tag;
import cn.dblearn.blog.entity.operation.vo.TagVO;
import cn.dblearn.blog.mapper.operation.TagMapper;
import cn.dblearn.blog.portal.operation.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TagServiceImpl
 *

 * @description
 */
@Service("TagPortalService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {


    /**
     * 获取tagVoList
     *
     * @return
     */
    @Override
    public List<TagVO> listTagVo() {
        return baseMapper.listTagVo();
    }
}
