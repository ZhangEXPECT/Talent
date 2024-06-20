package com.example.talent.service;

import com.example.talent.module.entity.Subsidy;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.SubsidyVO;

import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-17
 * @version: 1.0
 */
public interface SubsidyService {

    ResultVO add(Subsidy subsidy);

    ResultVO delete(Integer subsidyId);

    ResultVO queryById(Integer subsidyId);

    ResultVO querySubsidyBYUserId(Integer userId);

    ResultVO querySubsidyBYUser(User user);

    ResultVO update(Subsidy subsidy);

    ResultVO queryBySelectActive(SubsidyVO subsidyVO);

    ResultVO countBySelectActive(SubsidyVO subsidyVO);
}
