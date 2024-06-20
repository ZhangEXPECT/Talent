package com.example.talent.module.vo;


import com.example.talent.module.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends User{
    public User user;
    public String token;
}
