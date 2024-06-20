package com.example.talent.module.vo;

import com.example.talent.module.entity.Policy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class PolicyVO extends Policy {
    private String keywords;
    private Date startTime;
    private Date endTime;
    private Integer currentPage;
    private Integer pageSize;
}
