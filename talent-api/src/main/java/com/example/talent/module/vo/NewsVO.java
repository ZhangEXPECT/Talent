package com.example.talent.module.vo;

import com.example.talent.module.entity.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.inject.New;
import java.util.Date;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsVO extends News {
    private String Keywords;
    private Date startTime;
    private Date endTime;
    private Integer currentPage;
    private Integer pageSize;
}
