package com.example.talent.module.vo;

import com.example.talent.module.entity.Vr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-15
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VrVO extends Vr implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
}
