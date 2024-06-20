package com.example.talent.module.vo;

import com.example.talent.module.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: UserVO
 * @author: 张振彬
 * @create: 2023-02-15
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends User implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
}
