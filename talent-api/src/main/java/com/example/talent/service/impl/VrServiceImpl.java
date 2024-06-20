package com.example.talent.service.impl;

import com.example.talent.dao.VrDao;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Vr;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.VrVO;
import com.example.talent.service.VrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-05-15
 * @version: 1.0
 */
@Service
public class VrServiceImpl implements VrService {

    @Autowired
    private VrDao vrDao;

    @Override
    public ResultVO add(Vr vr) {
        vrDao.add(vr);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @Override
    public ResultVO delete(Integer vrId) {
        vrDao.delete(vrId);
        return new ResultVO(ResultEnum.DELETE_SUCCESS);
    }

    @Override
    public ResultVO queryId(Integer vrId) {
        Vr vr = vrDao.queryId(vrId);
        return new ResultVO(ResultEnum.SUCCESS, vr);
    }

    @Override
    public ResultVO update(Vr vr) {
        vrDao.update(vr);
        return new ResultVO(ResultEnum.UPDATE_SUCCESS);
    }

    @Override
    public ResultVO queryVrBYHouseId(Integer houseId) {
        Vr vr = vrDao.queryVrBYHouseId(houseId);
        return new ResultVO(ResultEnum.SUCCESS, vr);
    }

    @Override
    public ResultVO queryBySelectActive(VrVO vrVo) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(vrVo.getCurrentPage());
        pageBeans.setPageSize(vrVo.getPageSize());
        pageBeans.setCount(vrDao.countBySelectActive(vrVo));
        pageBeans.setData(vrDao.queryBySelectActive(vrVo));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

    @Override
    public ResultVO countBySelectActive(VrVO vrVo) {
        Integer i = vrDao.countBySelectActive(vrVo);
        return new ResultVO(ResultEnum.SUCCESS, i);
    }
}
