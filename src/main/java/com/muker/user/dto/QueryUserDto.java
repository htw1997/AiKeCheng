package com.muker.user.dto;

import lombok.Data;

/**
 * @ClassName QueryUserDto
 * @Description
 * @Author JavaQ
 * @Date 2019/12/18 11:17
 **/
@Data
public class QueryUserDto {
    private int id;
    private String phone;
    private String username;
    private int pageNum;
    private int pageSize;
}
