package com.example.talent.service;

import com.example.talent.module.entity.Qualification;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.QualificationVO;
import com.example.talent.module.vo.ResultVO;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-15
 * @version: 1.0
 */
public interface QualificationService {

    ResultVO add(Qualification qualification);

    ResultVO delete(Integer qualificationId);

    ResultVO queryById(Integer qualificationId);

    ResultVO queryQualificationBYUserId(Integer userId);

    ResultVO queryQualificationBYUser(User user);

    ResultVO update(Qualification qualification);

    ResultVO queryBySelectActive(QualificationVO qualificationVO);

    ResultVO countBySelectActive(QualificationVO qualificationVO);
}
