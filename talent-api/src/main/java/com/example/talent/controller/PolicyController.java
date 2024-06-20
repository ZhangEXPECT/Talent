package com.example.talent.controller;

import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.PolicyVO;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.PolicyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;


    @PostMapping("/add")
    @ApiOperation("添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "policy", value = "policy", dataType = "Integer"),
    })
    public ResultVO addPolicy(@RequestBody Policy policy) {
        policyService.add(policy);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @GetMapping("/delete")
    @ApiOperation("删除")
    public ResultVO deletePolicy(@PathVariable Integer policyId) {
        policyService.delete(policyId);
        return new ResultVO(ResultEnum.DELETE_SUCCESS);
    }


    @GetMapping("/queryById/{policyId}")
    @ApiOperation("Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "policyId", value = "policyId", dataType = "Integer"),
    })
    public ResultVO queryById(@PathVariable Integer policyId) {
        return new ResultVO(ResultEnum.SUCCESS, policyService.queryById(policyId));
    }


    @PostMapping("/getPolicy")
    @ApiOperation("列表查询")
    public ResultVO getPolicy(@RequestBody PolicyVO policyVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(policyVO.getCurrentPage());
        pageBeans.setPageSize(policyVO.getPageSize());
        pageBeans.setCount(policyService.countBySelectActive(policyVO));
        pageBeans.setData(policyService.queryBySelectActive(policyVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }
}
