package com.example.talent.service;

import com.example.talent.module.entity.Appoint;
import com.example.talent.module.vo.AppointVO;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-18
 * @version: 1.0
 */
public interface AppointService {
    void add(Appoint appoint);

    void delete(Integer appointId);

    void update(Appoint appoint);

    Appoint queryById(Integer appointId);

    Appoint queryByUserId(Integer userId);

    Integer queryByHouseId(Integer houseId);

    List<Appoint> queryByStatus(AppointVO appointVO);

    List<Appoint> queryByTime(AppointVO appointVO);
}
