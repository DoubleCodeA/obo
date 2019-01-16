package com.obo.base.controller;

import com.obo.base.pojo.Label;
import com.obo.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> labelList = labelService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",labelList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        Label label = labelService.findById(id);
        return new Result(true, StatusCode.OK,"查询成功",label);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true, StatusCode.OK,"增加成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable("id") String id){
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * @Author Double
     * @Description //TODO 根据条件查询
     * @Date 17:07 2019/01/16
     * @param
     * @return
    **/
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findSearch(searchMap));
    }

    /**
     * @Author Double
     * @Description //TODO 根据分页，条件查询
     * @Date 17:15 2019/01/16
     * @param
     * @return
    **/
    @RequestMapping(value = "/search/{pageNum}/{pageSize}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,
                             @PathVariable("pageNum") int pageNum,
                             @PathVariable("pageSize") int pageSize){
        Page<Label> pageList = labelService.findSearch(searchMap, pageNum, pageSize);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }
}
