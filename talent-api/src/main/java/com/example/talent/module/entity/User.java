package com.example.talent.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 用户实体类
 * @author: 张振彬
 * @create: 2023-2-15
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
    private String nick;//昵称
    private String phone;
    private String avatar;//头像
    private String gender;
    private String IDCard;
    private Date birthday;
    private String realName;
    private String certified;
}
