package com.example.talent.service.impl;

import com.example.talent.dao.ImgDao;
import com.example.talent.module.entity.Img;
import com.example.talent.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2022-12-25
 * @version: 1.0
 */

@Service
@Transactional
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgDao imgDao;
    @Override
    public Img queryById(Integer imgId) {
        return this.imgDao.queryById(imgId);
    }
    @Override
    public void add(Img img){
        this.imgDao.add(img);
    }
    @Override
    public List<Img> queryCondition(Img img){
        return this.imgDao.queryCondition(img);
    }
}
