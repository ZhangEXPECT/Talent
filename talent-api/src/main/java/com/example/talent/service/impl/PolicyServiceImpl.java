package com.example.talent.service.impl;

import com.example.talent.dao.PolicyDao;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.PolicyVO;
import com.example.talent.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyDao policyDao;


    @Override
    public void add(Policy policy) {
        policyDao.add(policy);
    }

    @Override
    public void delete(Integer policyId) {
        policyDao.delete(policyId);
    }

    @Override
    public Policy queryById(Integer policyId) {
        return policyDao.queryById(policyId);
    }

    @Override
    public List<Policy> queryByTime(Date startTime, Date endTime) {
        return policyDao.queryByTime(startTime, endTime);
    }

    @Override
    public List<Policy> queryBySelectActive(PolicyVO policyVO) {
        return policyDao.queryBySelectActive(policyVO);
    }

    @Override
    public Integer countBySelectActive(PolicyVO policyVO) {
        return policyDao.countBySelectActive(policyVO);
    }
}
