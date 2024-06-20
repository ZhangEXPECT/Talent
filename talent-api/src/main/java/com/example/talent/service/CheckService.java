package com.example.talent.service;

import com.example.talent.module.entity.Check;
import com.example.talent.module.vo.CheckVO;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
public interface CheckService {

    void add(Check check);

    void delete(Integer checkId);

    void update(Check check);

    Check queryById(Integer checkId);

    Check queryByUserId(Integer userId);

    Integer queryByHouseId(Integer houseId);

    List<Check> queryByStatus(CheckVO checkVO);

    List<Check> queryByTime(CheckVO checkVO);
}
