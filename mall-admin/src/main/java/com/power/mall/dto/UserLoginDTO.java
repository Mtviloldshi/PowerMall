package com.power.mall.dto;

import lombok.Data;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/30 15:38
 */
@Data
public class UserLoginDTO {

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;

    private String password;

}
