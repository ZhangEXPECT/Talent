package com.example.talent.dao;

import com.example.talent.module.entity.Admin;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.PolicyVO;
import com.example.talent.module.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface PolicyDao {

    void add(Policy policy);

    void delete(Integer policyId);

    Policy queryById(Integer policyId);

    List<Policy> queryByTime(Date startTime,Date endTime);

    List<Policy> queryBySelectActive(PolicyVO policyVO);

    Integer countBySelectActive(PolicyVO policyVO);
}
