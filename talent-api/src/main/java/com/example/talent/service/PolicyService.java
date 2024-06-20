package com.example.talent.service;

import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.PolicyVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
public interface PolicyService {

    void add(Policy policy);

    void delete(Integer policyId);

    Policy queryById(Integer policyId);

    List<Policy> queryByTime(Date startTime, Date endTime);

    List<Policy> queryBySelectActive(PolicyVO policyVO);

    Integer countBySelectActive(PolicyVO policyVO);
}
