package com.example.talent.module.dto;

import com.example.talent.module.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: 张振彬
 * @create: 2022-12-26
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends User {
    private String search;

}
