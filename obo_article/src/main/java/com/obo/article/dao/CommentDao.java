package com.obo.article.dao;

import com.obo.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Auther: Double
 * @Date: 2019/01/18 18:28
 * @Description:
 */
public interface CommentDao extends MongoRepository<Comment,String> {
    List<Comment> findByArticleid(String articleid);
}
