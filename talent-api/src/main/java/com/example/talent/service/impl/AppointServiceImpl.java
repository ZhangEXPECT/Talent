package com.example.talent.service.impl;

import com.example.talent.dao.AppointDao;
import com.example.talent.module.entity.Appoint;
import com.example.talent.module.vo.AppointVO;
import com.example.talent.service.AppointService;
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
public class AppointServiceImpl implements AppointService {

    @Resource
    private AppointDao appointDao;


    @Override
    public void add(Appoint appoint) {
        appointDao.add(appoint);
    }

    @Override
    public void delete(Integer appointId) {
        appointDao.delete(appointId);
    }

    @Override
    public void update(Appoint appoint) {
        appointDao.update(appoint);
    }

    @Override
    public Appoint queryById(Integer appointId) {
        return appointDao.queryById(appointId);
    }

    @Override
    public Appoint queryByUserId(Integer userId) {
        return appointDao.queryByUserId(userId);
    }

    @Override
    public Integer queryByHouseId(Integer houseId) {
        return appointDao.queryByHouseId(houseId);
    }

    @Override
    public List<Appoint> queryByStatus(AppointVO appointVO) {
        return appointDao.queryByStatus(appointVO);
    }

    @Override
    public List<Appoint> queryByTime(AppointVO appointVO) {
        return appointDao.queryByTime(appointVO);
    }
}
