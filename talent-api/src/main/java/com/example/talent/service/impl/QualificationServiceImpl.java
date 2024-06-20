package com.example.talent.service.impl;

import com.example.talent.dao.QualificationDao;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Qualification;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.QualificationVO;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-15
 * @version: 1.0
 */
@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationDao qualificationDao;

    @Override
    public ResultVO add(Qualification qualification) {
        qualificationDao.add(qualification);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @Override
    public ResultVO delete(Integer qualificationId) {
        if (qualificationDao.queryById(qualificationId) == null) {
            return new ResultVO(ResultEnum.DELETE_FAIL, "表单不存在");
        } else {
            qualificationDao.delete(qualificationId);
            return new ResultVO(ResultEnum.DELETE_SUCCESS);
        }
    }

    @Override
    public ResultVO queryById(Integer qualificationId) {
        return new ResultVO(ResultEnum.SUCCESS, qualificationDao.queryById(qualificationId));
    }

    @Override
    public ResultVO queryQualificationBYUserId(Integer userId) {
        return new ResultVO(ResultEnum.SUCCESS, qualificationDao.queryQualificationBYUserId(userId));
    }

    @Override
    public ResultVO queryQualificationBYUser(User user) {
        return new ResultVO(ResultEnum.SUCCESS, qualificationDao.queryQualificationBYUser(user));

    }

    @Override
    public ResultVO update(Qualification qualification) {
        qualificationDao.update(qualification);
        return new ResultVO(ResultEnum.UPDATE_SUCCESS);
    }

    @Override
    public ResultVO queryBySelectActive(QualificationVO qualificationVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(qualificationVO.getCurrentPage());
        pageBeans.setPageSize(qualificationVO.getPageSize());
        pageBeans.setCount(qualificationDao.countBySelectActive(qualificationVO));
        pageBeans.setData(qualificationDao.queryBySelectActive(qualificationVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

    @Override
    public ResultVO countBySelectActive(QualificationVO qualificationVO) {
        return new ResultVO(ResultEnum.SUCCESS, qualificationDao.countBySelectActive(qualificationVO));
    }
}
