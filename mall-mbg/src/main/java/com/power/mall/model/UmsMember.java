package com.power.mall.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/27 12:32
 */
@Data
public class UmsMember implements Serializable{

    private Long id;

    private String username;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private Date createTime;

    private Date loginTime;

    private Integer status;

}
