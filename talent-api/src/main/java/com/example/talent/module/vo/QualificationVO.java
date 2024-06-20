package com.example.talent.module.vo;

import com.example.talent.module.entity.Qualification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-17
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QualificationVO extends Qualification implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
}
