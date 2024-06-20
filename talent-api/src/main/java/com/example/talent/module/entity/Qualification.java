package com.example.talent.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 资格审核
 * @author: 张振彬
 * @create: 2023-04-15
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualification implements Serializable {
    private Integer qualificationId;
    private Integer userId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date buildTime;
    private String status;
    private String remark;
    private String married;
    private String local;
    private String school;
    private String degree;
    private String degreeInfo;
    private String major;
    private String employed;
    private String likeArea;
    private String likeCommunity;
    private String likeType;
    private String companyName;
    private String companyAddress;
    private String companyCode;
    private String companyContact;
    private String companyPhone;
    private String graduateImg;
    private String degreeImg;
    private String otherImg;
    private User user;
}
