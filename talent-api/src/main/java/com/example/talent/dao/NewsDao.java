package com.example.talent.dao;

import com.example.talent.module.entity.News;
import com.example.talent.module.entity.Policy;
import com.example.talent.module.vo.NewsVO;
import com.example.talent.module.vo.PolicyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface NewsDao {

    void add(News news);

    void delete(Integer newsId);

    News queryById(Integer newsId);

    List<News> queryByTime(Date startTime, Date endTime);

    List<News> queryBySelectActive(NewsVO newsVO);

    Integer countBySelectActive(NewsVO newsVO);
}
