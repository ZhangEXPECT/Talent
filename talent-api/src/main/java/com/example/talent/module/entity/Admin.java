package com.example.talent.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data//get,set方法
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private Integer adminId;
    private String adminName;
    private String adminAcc;
    private String adminPwd;
}
