package com.example.talent.dao;

import com.example.talent.module.entity.Qualification;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.QualificationVO;
import com.example.talent.module.vo.ResultVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualificationDao {

    void add(Qualification qualification);

    void delete(Integer qualificationId);

    Qualification queryById(Integer qualificationId);

    Qualification queryQualificationBYUserId(Integer userId);

    List<Qualification> queryQualificationBYUser(User user);

    void update(Qualification qualification);

    List<Qualification> queryBySelectActive(QualificationVO qualificationVO);

    Integer countBySelectActive(QualificationVO qualificationVO);
}
