package com.obo.spit.dao;

import com.obo.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: Double
 * @Date: 2019/01/18 15:59
 * @Description:
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    public Page<Spit> findByParentid(String id, Pageable pageable);
}
