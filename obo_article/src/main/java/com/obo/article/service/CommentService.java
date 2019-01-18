package com.obo.article.service;

import com.obo.article.dao.CommentDao;
import com.obo.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Double
 * @Date: 2019/01/18 18:29
 * @Description:
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Author Double
     * @Description //TODO 新增文章评论及子评论
     * @Date 18:34 2019/01/18
     * @param
     * @return
    **/
    public void addComment(String parentId, Comment comment){
        comment.set_id(String.valueOf(idWorker.nextId()));
        comment.setPublishdate(new Date());
        if (!StringUtils.isEmpty(parentId)){
            comment.setParentid(parentId);
        }
        commentDao.save(comment);
    }

    /**
     * @Author Double
     * @Description //TODO 根据文章ID查询评论列表
     * @Date 18:41 2019/01/18
     * @param
     * @return
    **/
    public List<Comment> findByArticleid(String articleid){
       return commentDao.findByArticleid(articleid);
    }

    /**
     * @Author Double
     * @Description //TODO 根据评论ID删除评论及其子评论
     * @Date 18:52 2019/01/18
     * @param
     * @return
    **/
    public void deleteById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").and("parentid").is(id));
        mongoTemplate.remove(query, "comment");
    }
}
