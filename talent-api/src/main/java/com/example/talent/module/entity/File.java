package com.example.talent.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    Integer id;
    String url;
    String type;
    Timestamp createTime;
    Integer pId;
}
