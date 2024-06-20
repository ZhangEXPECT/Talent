package com.example.talent.dao;

import com.example.talent.module.entity.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface HouseDao {

    void add(House house);

    void update(House house);

    void delete(Integer houseId);

    House queryById(Integer houseId);

    List<House> queryCondition(House house);

    List<House> queryByArea(Integer minHouseArea, Integer maxHouseArea);

    List<House> queryByPrice(Double minPrice, Double maxPrice);

    Integer queryCount();

    //   List<House> queryByPage(Integer pageStart, Integer pageSize);

    House getImgByHouseId(Integer houseId);

    List<House> changeHouseStatus();

    List<House> queryByStatus(String houseStatus);

    List<House> queryAll();

    List<House> getHouse();

    void changeStatusById(Integer houseId);
}