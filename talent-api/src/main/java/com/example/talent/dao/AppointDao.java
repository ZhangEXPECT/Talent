package com.example.talent.dao;

import com.example.talent.module.entity.Appoint;
import com.example.talent.module.vo.AppointVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AppointDao {

    void add(Appoint appoint);

    void delete(Integer appointId);

    void update(Appoint appoint);

    Appoint queryById(Integer appointId);

    Appoint queryByUserId(Integer userId);

    Integer queryByHouseId(Integer houseId);

    List<Appoint> queryByStatus(AppointVO appointVO);

    List<Appoint> queryByTime(AppointVO appointVO);
}
