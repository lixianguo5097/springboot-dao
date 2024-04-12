package com.lxg.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @create: 2021/07/26 16:31
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String userName;
    private String passWord;
    private String phone;
    private String createDay;
}