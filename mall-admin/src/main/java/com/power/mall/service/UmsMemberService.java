package com.power.mall.service;

import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.model.UmsMember;

import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/27 12:10
 */

public interface UmsMemberService {
    public Boolean register(UserRegisterDTO userRegisterDTO);

    String login(UserLoginDTO loginDTO);

    List<UmsMember> list();

    UmsMember getCurrentMember();
}
