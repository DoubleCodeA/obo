package com.obo.base.dao;

import com.obo.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelDAO extends JpaRepository<Label,String> {
}
