package cn.dblearn.blog.entity.book.dto;

import cn.dblearn.blog.entity.book.Book;
import cn.dblearn.blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * ReadBookDto

 * @description
 */
@Data
public class BookDTO extends Book {

    List<Tag> tagList;
}
