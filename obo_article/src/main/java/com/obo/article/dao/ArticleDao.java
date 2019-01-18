package com.obo.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.obo.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(value = "update tb_article set state = '1' where id= ?1", nativeQuery = true)
    public void examine(String id);

    @Modifying
    @Query(value = "update Article set thumbup = thumbup + 1 where id = ?1", nativeQuery = true)
    public void updateThumbup(String id);
}
