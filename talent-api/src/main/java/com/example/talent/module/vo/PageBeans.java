package com.example.talent.module.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBeans<T> {
    private Integer currentPage;// 当前页
    private Integer pageSize;// 页面大小
    private Integer count;//总记录数
    private List<T> data;// 分页数据
}