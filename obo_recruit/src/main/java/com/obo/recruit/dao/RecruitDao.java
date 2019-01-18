package com.obo.recruit.dao;

import com.obo.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * @Author Double
     * @Description //TODO 查询推荐职位
     * @Date 17:08 2019/01/17
     * @param
     * @return
    **/
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

    /**
     * @Author Double
     * @Description //TODO 查询最新职位
     * @Date 17:08 2019/01/17
     * @param
     * @return
    **/
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
