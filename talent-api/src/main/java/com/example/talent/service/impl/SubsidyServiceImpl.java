package com.example.talent.service.impl;

import com.example.talent.dao.SubsidyDao;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Subsidy;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.SubsidyVO;
import com.example.talent.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-05-17
 * @version: 1.0
 */
@Service
public class SubsidyServiceImpl implements SubsidyService {

    @Autowired
    private SubsidyDao subsidyDao;


    @Override
    public ResultVO add(Subsidy subsidy) {
        subsidyDao.add(subsidy);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @Override
    public ResultVO delete(Integer subsidyId) {
        if (subsidyDao.queryById(subsidyId) == null) {
            return new ResultVO(ResultEnum.DELETE_FAIL, "表单不存在");
        } else {
            subsidyDao.delete(subsidyId);
            return new ResultVO(ResultEnum.DELETE_SUCCESS);
        }
    }

    @Override
    public ResultVO queryById(Integer subsidyId) {
        return new ResultVO(ResultEnum.SUCCESS, subsidyDao.queryById(subsidyId));
    }

    @Override
    public ResultVO querySubsidyBYUserId(Integer userId) {
        return new ResultVO(ResultEnum.SUCCESS, subsidyDao.querySubsidyBYUserId(userId));
    }

    @Override
    public ResultVO querySubsidyBYUser(User user) {
        return new ResultVO(ResultEnum.SUCCESS, subsidyDao.querySubsidyBYUser(user));
    }

    @Override
    public ResultVO update(Subsidy subsidy) {
        subsidyDao.update(subsidy);
        return new ResultVO(ResultEnum.UPDATE_SUCCESS);
    }

    @Override
    public ResultVO queryBySelectActive(SubsidyVO subsidyVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(subsidyVO.getCurrentPage());
        pageBeans.setPageSize(subsidyVO.getPageSize());
        pageBeans.setCount(subsidyDao.countBySelectActive(subsidyVO));
        pageBeans.setData(subsidyDao.queryBySelectActive(subsidyVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

    @Override
    public ResultVO countBySelectActive(SubsidyVO subsidyVO) {
        return new ResultVO(ResultEnum.SUCCESS, subsidyDao.countBySelectActive(subsidyVO));
    }
}
