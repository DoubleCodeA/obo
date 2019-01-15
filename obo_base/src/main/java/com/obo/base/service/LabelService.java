package com.obo.base.service;

import com.obo.base.dao.LabelDAO;
import com.obo.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

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
}
