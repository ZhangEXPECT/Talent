package com.example.talent.service;

import com.example.talent.module.entity.Img;

import java.util.List;

public interface ImgService {
    Img queryById(Integer imgId);
    void add(Img img);
    List<Img> queryCondition(Img img);
}
