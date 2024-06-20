package com.example.talent.dao;

import com.example.talent.module.entity.Check;
import com.example.talent.module.vo.CheckVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-19
 * @version: 1.0
 */
@Mapper
public interface CheckDao {

    void add(Check check);

    void delete(Integer checkId);

    void update(Check check);

    Check queryById(Integer checkId);

    Check queryByUserId(Integer userId);

    Integer queryByHouseId(Integer houseId);

    List<Check> queryByStatus(CheckVO checkVO);

    List<Check> queryByTime(CheckVO checkVO);

}
