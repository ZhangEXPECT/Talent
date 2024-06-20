package com.example.talent.controller;

import com.example.talent.enums.ResultEnum;
import com.example.talent.module.entity.News;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.NewsVO;
import com.example.talent.module.vo.PageBeans;
import com.example.talent.module.vo.PolicyVO;
import com.example.talent.module.vo.ResultVO;
import com.example.talent.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    @ApiOperation("添加")
    public ResultVO addNews(@RequestBody News news) {
        newsService.add(news);
        return new ResultVO(ResultEnum.ADD_SUCCESS);
    }

    @GetMapping("/delete")
    @ApiOperation("删除")
    public ResultVO deletePolicy(@PathVariable Integer newsId) {
        newsService.delete(newsId);
        return new ResultVO(ResultEnum.DELETE_SUCCESS);
    }


    @GetMapping("/queryById/{newsId}")
    @ApiOperation("Id查询")
    public ResultVO queryById(@PathVariable Integer newsId) {
        return new ResultVO(ResultEnum.SUCCESS, newsService.queryById(newsId));
    }

    @PostMapping("/getNews")
    @ApiOperation("列表查询")
    public ResultVO getNews(@RequestBody NewsVO newsVO) {
        PageBeans pageBeans = new PageBeans();
        pageBeans.setCurrentPage(newsVO.getCurrentPage());
        pageBeans.setPageSize(newsVO.getPageSize());
        pageBeans.setCount(newsService.countBySelectActive(newsVO));
        pageBeans.setData(newsService.queryBySelectActive(newsVO));
        return new ResultVO(ResultEnum.SUCCESS, pageBeans);
    }

}
