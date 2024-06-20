package com.example.talent.service.impl;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */

import com.example.talent.dao.NewsDao;
import com.example.talent.module.entity.News;
import com.example.talent.module.vo.NewsVO;
import com.example.talent.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public void add(News news) {
        newsDao.add(news);
    }

    @Override
    public void delete(Integer newsId) {
        newsDao.delete(newsId);
    }

    @Override
    public News queryById(Integer newsId) {
        return newsDao.queryById(newsId);
    }

    @Override
    public List<News> queryByTime(Date startTime, Date endTime) {
        return newsDao.queryByTime(startTime, endTime);
    }

    @Override
    public List<News> queryBySelectActive(NewsVO newsVO) {
        return newsDao.queryBySelectActive(newsVO);
    }

    @Override
    public Integer countBySelectActive(NewsVO newsVO) {
        return newsDao.countBySelectActive(newsVO);
    }
}
