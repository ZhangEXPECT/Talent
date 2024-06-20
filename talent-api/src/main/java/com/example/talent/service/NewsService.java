package com.example.talent.service;

import com.example.talent.module.entity.News;
import com.example.talent.module.vo.NewsVO;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-05-16
 * @version: 1.0
 */
public interface NewsService {

    void add(News news);

    void delete(Integer newsId);

    News queryById(Integer newsId);

    List<News> queryByTime(Date startTime, Date endTime);

    List<News> queryBySelectActive(NewsVO newsVO);

    Integer countBySelectActive(NewsVO newsVO);
}
