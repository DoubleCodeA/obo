package com.obo.article.controller;

import com.obo.article.pojo.Comment;
import com.obo.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Double
 * @Date: 2019/01/18 19:54
 * @Description:
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * @Author Double
     * @Description //TODO 增加评论
     * @Date 20:02 2019/01/18
     * @param
     * @return
    **/
    @RequestMapping(value = "/{parentId}", method = RequestMethod.POST)
    public Result addComment(@PathVariable("parentId") String parentId, Comment comment){
        commentService.addComment(parentId, comment);
        return new Result(true, StatusCode.OK,"评论成功");
    }

    /**
     * @Author Double
     * @Description //TODO 查询评论
     * @Date 20:02 2019/01/18
     * @param
     * @return
    **/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findByArticleid(@PathVariable("id") String id){
        commentService.findByArticleid(id);
        return new Result(true, StatusCode.OK,"查询成功");
    }

    /**
     * @Author Double
     * @Description //TODO 删除评论
     * @Date 20:04 2019/01/18
     * @param
     * @return
    **/
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("id") String id){
        commentService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除评论成功");
    }


}
