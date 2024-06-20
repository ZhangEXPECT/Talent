package com.example.talent.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: VR实体类
 * @author: 张振彬
 * @create: 2023-02-15
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vr implements Serializable {
    private Integer vrId;
    private Integer houseId;
    private String vrUrl;
    private String vrContent;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;

}
