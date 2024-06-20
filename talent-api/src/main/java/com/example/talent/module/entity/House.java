package com.example.talent.module.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @description:
 * @author: 张振彬
 * @create: 2022-09-07
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House implements Serializable {
    private Integer houseId;
    private Integer clientId;
    private Integer empId;
    private Integer adminId;
    private String houseType;
    private Double housePrice;
    private Double houseArea;
    private String city;
    private String address;
    private String layoutType;
    private String cellName;
    private String houseOrientation;
    private Integer imageId;
    private String houseStatus;
    private Img img;


}
