package com.muker.vo;

import lombok.Data;

import java.util.List;


@Data
public class PageBean<T> {
    private int page;
    private int total;
    private int size;
    private long totalPage;
    private List<T> data;
}
