package com.obo.qa.dao;

import com.obo.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
	@Query(value = "select * from tb_problem  where replytime is not null and id in (select problemid from tb_pl where labelid = 1) ORDER BY createtime DESC",nativeQuery = true)
    public Page<Problem> findNewList(String id, Pageable pageable);

	@Query(value = "select * from tb_problem where EXISTS (select 1 from tb_pl where labelid = 1)  and reply!= 0 order by reply DESC",nativeQuery = true)
    public Page<Problem> findHotList(String id, Pageable pageable);

	@Query(value = "select * from tb_problem where Exists (select 1 from tb_pl where labelid = 1) and reply = 0 order by createtime DESC", nativeQuery = true)
    public Page<Problem> findWaitList(String id, Pageable pageable);
}
