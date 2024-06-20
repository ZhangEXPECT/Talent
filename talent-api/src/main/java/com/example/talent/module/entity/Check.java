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
 * @create: 2023-04-19
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Check implements Serializable {
    private Integer checkId;
    private Integer userId;
    private Integer houseId;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buildTime;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;
    private String status;
    private User user;
    private House house;
}
