package com.obo.spit.service;

import com.obo.spit.dao.SpitDao;
import com.obo.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Double
 * @Date: 2019/01/18 16:01
 * @Description:
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Author Double
     * @Description //TODO 查询全部吐槽
     * @Date 16:12 2019/01/18
     * @param
     * @return
    **/
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * @Author Double
     * @Description //TODO 根据ID查询吐槽并增加浏览数
     * @Date 16:12 2019/01/18
     * @param
     * @return
    **/
    public Spit findById(String id){
        Spit spit = spitDao.findById(id).get();
        this.update(id, "visits");
        return spit;
    }

    /**
     * @Author Double
     * @Description //TODO 增加吐槽
     * @Date 16:13 2019/01/18
     * @param
     * @return
    **/
    public void save(Spit spit){
        spit.set_id(String.valueOf(idWorker.nextId()));
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setThumbup(0);
        spit.setShare(0);
        spit.setComment(0);
        spit.setState("1");
        if(!StringUtils.isEmpty(spit.getParentid())){
            this.update(spit.get_id(), "comment");
        }
        spitDao.save(spit);
    }

    /**
     * @Author Double
     * @Description //TODO 根据ID删除吐槽
     * @Date 16:13 2019/01/18
     * @param 
     * @return  
    **/
    public void deleteById(String id){
        spitDao.deleteById(id);
    }


    /**
     * @Author Double
     * @Description //TODO 根据上级ID分页查询吐槽
     * @Date 16:42 2019/01/18
     * @param
     * @return
    **/
    public Page<Spit> findByParentid(String id, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(id,pageable);
    }

    /**
     * @Author Double
     * @Description //TODO 吐槽点赞
     * @Date 17:17 2019/01/18
     * @param 
     * @return  
    **/
    public void updateThumbup(String id){
        this.update(id, "thumbup");
    }

    /**
     * @Author Double
     * @Description //TODO 增加分享数
     * @Date 18:08 2019/01/18
     * @param
     * @return
    **/
   public void updateShare(String id){
        this.update(id, "share");
   }

   /**
    * @Author Double
    * @Description //TODO 更新分享数
    * @Date 18:08 2019/01/18
    * @param
    * @return
   **/
   protected void update(String id, String field){
       Query query = new Query();
       query.addCriteria(Criteria.where("_id").is(id));
       Update update = new Update();
       update.inc(field, 1);
       mongoTemplate.updateFirst(query, update, "spit");
   }

}
