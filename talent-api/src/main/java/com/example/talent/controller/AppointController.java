package com.example.talent.controller;

import com.example.talent.dao.AppointDao;
import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Appoint;
import com.example.talent.module.vo.AppointVO;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.AppointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@RestController
@RequestMapping("/appoint")
public class AppointController {

    @Autowired
    private AppointService  appointService;

    @PostMapping("/add")
    @ApiOperation("添加")
    public ResultVO addAppoint(@RequestBody Appoint appoint) {
        appointService.add(appoint);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @GetMapping("/delete/{appointId}")
    @ApiOperation("删除")
    public ResultVO deleteAppoint(@PathVariable Integer appointId) {
        appointService.delete(appointId);
        return new ResultVO(ResultEnum.DELETE_SUCCESS);
    }

    @PostMapping("/update")
    @ApiOperation("添加")
    public ResultVO updateAppoint(@RequestBody Appoint appoint) {
        appointService.update(appoint);
        return new ResultVO(ResultEnum.UPDATE_SUCCESS);
    }

    @GetMapping("/queryById/{appointId}")
    @ApiOperation("Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appointId", value = "appointId", dataType = "Integer"),
    })
    public ResultVO queryById(@PathVariable Integer appointId) {
        return new ResultVO(ResultEnum.SUCCESS, appointService.queryById(appointId));
    }

    @GetMapping("/queryByUserId/{userId}")
    @ApiOperation("userId查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", dataType = "Integer"),
    })
    public ResultVO queryByUserId(@PathVariable Integer userId) {
        return new ResultVO(ResultEnum.SUCCESS, appointService.queryByUserId(userId));
    }



    @PostMapping("/getAppointByStatus")
    @ApiOperation("列表查询")
    public ResultVO getAppointByStatus(@RequestBody AppointVO appointVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(appointVO.getCurrentPage());
        pageBeans.setPageSize(appointVO.getPageSize());
//        pageBeans.setCount(checkService.countBySelectActive(checkVO));
        pageBeans.setData(appointService.queryByStatus(appointVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

    @PostMapping("/getAppointByTime")
    @ApiOperation("列表查询")
    public ResultVO getAppointByTime(@RequestBody AppointVO appointVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(appointVO.getCurrentPage());
        pageBeans.setPageSize(appointVO.getPageSize());
//        pageBeans.setCount(checkService.countBySelectActive(checkVO));
        pageBeans.setData(appointService.queryByStatus(appointVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

}
