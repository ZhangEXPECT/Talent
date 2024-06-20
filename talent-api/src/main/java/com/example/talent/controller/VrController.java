package com.example.talent.controller;

import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.House;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.entity.Vr;
import com.example.talent.module.vo.*;
import com.example.talent.service.UserService;
import com.example.talent.service.VrService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-15
 * @version: 1.0
 */
@RestController
@RequestMapping("/vr")
public class VrController {

    @Autowired
    private VrService vrService;


    @PostMapping("/add")
    @ApiOperation("添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vr", value = "vr", dataType = "Integer"),
    })
    public ResultVO addVr(@RequestBody Vr vr) {
        return  vrService.add(vr);
    }

    @PostMapping("/update")
    @ApiOperation("编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vr", value = "vr", dataType = "Integer"),
    })
    public ResultVO updateVr(@RequestBody Vr vr) {
        return  vrService.update(vr);
    }


    @GetMapping("/delete/{vrId}")
    @ApiOperation("删除")
    public ResultVO deleteVr(@PathVariable Integer vrId) {
        return vrService.delete(vrId);
    }


    @GetMapping("/queryById/{vrId}")
    @ApiOperation("Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vrId", value = "vrId", dataType = "Integer"),
    })
    public ResultVO queryById(@PathVariable Integer vrId) {
        return vrService.queryId(vrId);
    }


    @PostMapping("/getVr")
    @ApiOperation("列表查询")
    public ResultVO getVr(@RequestBody VrVO vrVO) {
        return vrService.queryBySelectActive(vrVO);
    }
}
