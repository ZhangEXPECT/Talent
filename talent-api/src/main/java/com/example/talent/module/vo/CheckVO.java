package com.example.talent.module.vo;

import com.example.talent.module.entity.Check;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-18
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckVO extends Check implements Serializable {
    private String status;
    private Date startTime;
    private Date endTime;
    private Integer currentPage;
    private Integer pageSize;
}
