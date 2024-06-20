package com.example.talent.service;


import com.example.talent.module.entity.House;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.ResultVO;

import java.util.*;

public interface HouseService {


    ResultVO add(House house);

    ResultVO update(House house);

    ResultVO delete(Integer houseId);

    House queryById(Integer houseId);

    House getImgByHouseId(Integer houseId);

    List<House> queryCondition(House house);

    List<House> queryByType(String houseType);

    List<House> queryByCity(String city);

    List<House> queryByArea(Integer minHouseArea, Integer maxHouseArea);

    List<House> queryByPrice(Double minPrice, Double maxPrice);

    Integer queryCount();

    PageBeans<House> queryByPage(House house, Integer pageStart, Integer pageSize);

    List<House> changeHouseStatus();

    List<House> queryByStatus(String houseStatus);

    List<House> queryAll();

    List<House> getHouse();

    ResultVO changeStatusById(Integer houseId);
}