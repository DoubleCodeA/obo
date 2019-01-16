package com.obo.base.service;

import com.obo.base.dao.LabelDAO;
import com.obo.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    @Autowired
    private LabelDAO labelDAO;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDAO.findAll();
    }

    public Label findById(String id){
        return labelDAO.findById(id).get();
    }

    public void add(Label label){
        label.setId(String.valueOf(idWorker.nextId()));
        labelDAO.save(label);
    }

    public void update(Label label){
        labelDAO.save(label);
    }

    public void deleteById(String id){
        labelDAO.deleteById(id);
    }

    /**
     * 构件查询条件
     * @param searchMap
     * @return
     */
    public Specification<Label> createSpecification(Map searchMap){
        Specification<Label> labelSpecification = new Specification<Label>() {

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> predicates = new ArrayList<>();
                if (searchMap.get("labelname") != null && !"".equals(searchMap.get("labelname"))) {
                    predicates.add(
                            criteriaBuilder.like(root.get("labelname").as(String.class),
                            "%"+(String)searchMap.get("labelname")+"%")
                    );
                }
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))){
                    predicates.add(
                            criteriaBuilder.equal(root.get("state").as(String.class),
                                    (String)searchMap.get("state"))
                    );
                }
                if (searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))){
                    predicates.add(
                            criteriaBuilder.equal(root.get("recommend").as(String.class),
                                    (String)searchMap.get("recommend"))
                    );
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return labelSpecification;
    }

    /**
     * @Author Double
     * @Description //TODO 根据条件查询
     * @Date 17:01 2019/01/16
     * @param null
     * @return
    **/
    public List<Label> findSearch(Map searchMap){
        Specification specification = createSpecification(searchMap);
        return labelDAO.findAll(specification);
    }

    /**
     * @Author Double
     * @Description //TODO 分页条件查询
     * @Date 17:09 2019/01/16
     * @param
     * @return
    **/
    public Page<Label> findSearch(Map searchMap, int pageNum, int pageSize){
        Specification<Label> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return labelDAO.findAll(specification,pageRequest);
    }

}
