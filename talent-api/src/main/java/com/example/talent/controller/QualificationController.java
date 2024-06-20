package com.example.talent.controller;

import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.entity.Qualification;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.PolicyVO;
import com.example.talent.module.vo.QualificationVO;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.QualificationService;
import com.example.talent.service.VrService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-05-15
 * @version: 1.0
 */
@RestController
@RequestMapping("/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @PostMapping("/add")
    @ApiOperation("添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qualification", value = "qualification", dataType = "Integer"),
    })
    public ResultVO addPolicy(@RequestBody Qualification qualification) {
        return qualificationService.add(qualification);
    }

    @GetMapping("/delete/{qualificationId}")
    @ApiOperation("删除")
    public ResultVO deletePolicy(@PathVariable Integer qualificationId) {
        return  qualificationService.delete(qualificationId);
    }

    @PostMapping("/update")
    @ApiOperation("列表查询")
    public ResultVO updateQualification(@RequestBody Qualification qualification) {
        return qualificationService.update(qualification);
    }



    @GetMapping("/queryById/{qualificationId}")
    @ApiOperation("Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qualificationId", value = "qualificationId", dataType = "Integer"),
    })
    public ResultVO queryById(@PathVariable Integer qualificationId) {
        return  qualificationService.queryById(qualificationId);
    }

    @GetMapping("/queryByUserId/{userId}")
    @ApiOperation("UserId查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", dataType = "Integer"),
    })
    public ResultVO queryByUserId(@PathVariable Integer userId) {
        return  qualificationService.queryQualificationBYUserId(userId);
    }

    @GetMapping("/queryByUser")
    @ApiOperation("User查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user", dataType = "User"),
    })
    public ResultVO queryByUser(@RequestBody User user) {
        return  qualificationService.queryQualificationBYUser(user);
    }


    @PostMapping("/getQualification")
    @ApiOperation("列表查询")
    public ResultVO getQualification(@RequestBody QualificationVO qualificationVO) {
        return qualificationService.queryBySelectActive(qualificationVO);
    }


}
