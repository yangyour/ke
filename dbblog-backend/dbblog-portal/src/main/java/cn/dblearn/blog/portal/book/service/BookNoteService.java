package cn.dblearn.blog.portal.book.service;

import cn.dblearn.blog.common.util.PageUtils;
import cn.dblearn.blog.entity.book.BookNote;
import cn.dblearn.blog.entity.book.vo.BookNoteVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * BookNoteAdminService
 *
 * @description
 */
public interface BookNoteService extends IService<BookNote> {

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取简单对象
     * @param bookNoteId
     * @return
     */
    BookNoteVO getSimpleBookNoteVo(Integer bookNoteId);

    /**
     * 获取简单List
     * @param bookId
     * @return
     */
    List<BookNote> listSimpleBookNote(Integer bookId);
}
