package cn.dblearn.blog.entity.article.dto;

import cn.dblearn.blog.entity.article.Article;
import cn.dblearn.blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

/**
 * ArticleDto
 *
 * @description
 */
@Data
public class ArticleDTO extends Article {

    private List<Tag> tagList;

}
