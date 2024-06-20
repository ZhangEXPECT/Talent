package com.example.talent.service.impl;

import com.example.talent.dao.CheckDao;
import com.example.talent.module.entity.Check;
import com.example.talent.module.vo.CheckVO;
import com.example.talent.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckDao checkDao;

    @Override
    public void add(Check check) {
        checkDao.add(check);
    }

    @Override
    public void delete(Integer checkId) {
        checkDao.delete(checkId);
    }

    @Override
    public void update(Check check) {
        checkDao.update(check);
    }

    @Override
    public Check queryById(Integer checkId) {
        return checkDao.queryById(checkId);
    }

    @Override
    public Check queryByUserId(Integer userId) {
        return checkDao.queryByUserId(userId);
    }

    @Override
    public Integer queryByHouseId(Integer houseId) {
        return checkDao.queryByHouseId(houseId);
    }

    @Override
    public List<Check> queryByStatus(CheckVO checkVO) {
        return checkDao.queryByStatus(checkVO);
    }

    @Override
    public List<Check> queryByTime(CheckVO checkVO) {
        return checkDao.queryByTime(checkVO);
    }
}
