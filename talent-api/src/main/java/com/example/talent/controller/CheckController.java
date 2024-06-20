package com.example.talent.controller;


import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Appoint;
import com.example.talent.module.entity.Check;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.CheckVO;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.PolicyVO;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.CheckService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private CheckService checkService;


    @PostMapping("/add")
    @ApiOperation("添加")
    public ResultVO addCheck(@RequestBody Check check) {
        checkService.add(check);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @GetMapping("/delete/{checkId}")
    @ApiOperation("删除")
    public ResultVO deleteCheck(@PathVariable Integer checkId) {
        checkService.delete(checkId);
        return new ResultVO(ResultEnum.DELETE_SUCCESS);
    }

    @PostMapping("/update")
    @ApiOperation("添加")
    public ResultVO updateCheck(@RequestBody  Check check) {
        checkService.update(check);
        return new ResultVO(ResultEnum.UPDATE_SUCCESS);
    }


    @GetMapping("/queryById/{checkId}")
    @ApiOperation("Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkId", value = "checkId", dataType = "Integer"),
    })
    public ResultVO queryById(@PathVariable Integer checkId) {
        return new ResultVO(ResultEnum.SUCCESS, checkService.queryById(checkId));
    }


    @GetMapping("/queryByUserId/{userId}")
    @ApiOperation("userId查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", dataType = "Integer"),
    })
    public ResultVO queryByUserId(@PathVariable Integer userId) {
        return new ResultVO(ResultEnum.SUCCESS, checkService.queryByUserId(userId));
    }



    @PostMapping("/getCheckByStatus")
    @ApiOperation("列表查询")
    public ResultVO getCheckByStatus(@RequestBody CheckVO checkVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(checkVO.getCurrentPage());
        pageBeans.setPageSize(checkVO.getPageSize());
//        pageBeans.setCount(checkService.countBySelectActive(checkVO));
        pageBeans.setData(checkService.queryByStatus(checkVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

    @PostMapping("/getCheckByTime")
    @ApiOperation("列表查询")
    public ResultVO getCheckByTime(@RequestBody CheckVO checkVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(checkVO.getCurrentPage());
        pageBeans.setPageSize(checkVO.getPageSize());
//        pageBeans.setCount(checkService.countBySelectActive(checkVO));
        pageBeans.setData(checkService.queryByStatus(checkVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

}
