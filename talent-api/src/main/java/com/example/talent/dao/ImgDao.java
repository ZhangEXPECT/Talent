package com.example.talent.dao;


import com.example.talent.module.entity.Img;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ImgDao {
    Img queryById(Integer imgId);
    void add(Img img);
    List<Img> queryCondition(Img img);
}
