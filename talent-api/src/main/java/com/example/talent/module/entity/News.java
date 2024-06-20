package com.example.talent.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class News implements Serializable {
    private Integer newsId;
    private String title;
    private String content;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date releaseTime;
    private String img1;
    private String img2;
    private String img3;
}
