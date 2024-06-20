package com.example.talent.controller;


import com.example.talent.module.entity.Subsidy;
import com.example.talent.module.entity.User;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.module.vo.SubsidyVO;
import com.example.talent.service.SubsidyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-05-16
 * @version: 1.0
 */
@RestController
@RequestMapping("/subsidy")
public class SubsidyController {
    @Autowired
    private SubsidyService subsidyService;

    @PostMapping("/add")
    @ApiOperation("添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subsidy", value = "subsidy", dataType = "Integer"),
    })
    public ResultVO addSubsidy(@RequestBody Subsidy subsidy) {
        return subsidyService.add(subsidy);
    }

    @GetMapping("/delete/{subsidyId}")
    @ApiOperation("删除")
    public ResultVO deleteSubsidy(@PathVariable Integer subsidyId) {
        return  subsidyService.delete(subsidyId);
    }

    @PostMapping("/update")
    @ApiOperation("列表查询")
    public ResultVO updateSubsidy(@RequestBody Subsidy subsidy) {
        return subsidyService.update(subsidy);
    }



    @GetMapping("/queryById/{subsidyId}")
    @ApiOperation("Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subsidyId", value = "subsidyId", dataType = "Integer"),
    })
    public ResultVO queryById(@PathVariable Integer subsidyId) {
        return  subsidyService.queryById(subsidyId);
    }

    @GetMapping("/queryByUserId/{userId}")
    @ApiOperation("UserId查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", dataType = "Integer"),
    })
    public ResultVO queryByUserId(@PathVariable Integer userId) {
        return  subsidyService.querySubsidyBYUserId(userId);
    }

    @GetMapping("/queryByUser")
    @ApiOperation("User查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user", dataType = "User"),
    })
    public ResultVO queryByUser(@RequestBody User user) {
        return  subsidyService.querySubsidyBYUser(user);
    }




    @PostMapping("/getSubsidy")
    @ApiOperation("列表查询")
    public ResultVO getSubsidy(@RequestBody SubsidyVO subsidyVO) {
        return subsidyService.queryBySelectActive(subsidyVO);
    }

}
