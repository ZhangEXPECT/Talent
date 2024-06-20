package com.example.talent.dao;

import com.example.talent.module.entity.Qualification;
import com.example.talent.module.entity.Subsidy;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.QualificationVO;
import com.example.talent.module.vo.SubsidyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubsidyDao {

    void add(Subsidy subsidy);

    void delete(Integer subsidyId);

    Subsidy queryById(Integer subsidyId);

    Subsidy querySubsidyBYUserId(Integer userId);

    List<Subsidy> querySubsidyBYUser(User user);

    void update(Subsidy subsidy);

    List<Subsidy> queryBySelectActive(SubsidyVO subsidyVO);

    Integer countBySelectActive(SubsidyVO subsidyVO);
}
