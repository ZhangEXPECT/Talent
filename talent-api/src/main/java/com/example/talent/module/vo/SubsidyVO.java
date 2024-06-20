package com.example.talent.module.vo;

import com.example.talent.module.entity.Subsidy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @description:
 * @author: 张振彬
 * @create: 2023-04-16
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubsidyVO extends Subsidy implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
}
