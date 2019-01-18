package com.obo.spit.controller;

import com.obo.spit.pojo.Spit;
import com.obo.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Double
 * @Date: 2019/01/18 16:01
 * @Description:
 */
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("id") String id){
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/comment/{id}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable("id") String id, @PathVariable("page") int page, @PathVariable("size") int size){
        Page<Spit> pages = spitService.findByParentid(id, page, size);
        PageResult<Spit> pageResult = new PageResult<>(pages.getTotalElements(), pages.getContent());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * @Author Double
     * @Description //TODO 点赞吐槽，并控制不能重复点赞
     * @Date 17:33 2019/01/18
     * @param
     * @return
    **/
    @RequestMapping(value = "/thumbup/{id}" , method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable("id") String id){
        String userid="123";
        if (redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id) != null){
            return new Result(true, StatusCode.REPERROR,"不能重复点赞");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set( "thumbup_"+userid+"_"+ id,"1");
        return new Result(true, StatusCode.OK,"点赞成功");
    }


    @RequestMapping(value = "/share/{id}", method = RequestMethod.PUT)
    public void updateShare(@PathVariable("id") String id){
        spitService.updateShare(id);
    }

}
