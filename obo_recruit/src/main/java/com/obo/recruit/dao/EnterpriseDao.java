package com.obo.recruit.dao;

import com.obo.recruit.pojo.Enterprise;
import com.obo.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    /**
     * @Author Double
     * @Description //TODO 获取热门企业--根据ishot
     * @Date 16:17 2019/01/17
     * @param [ishot]
     * @return java.util.List<com.obo.recruit.pojo.Recruit>
    **/
    public List<Enterprise> findTop12ByIshot(String ishot);

}
