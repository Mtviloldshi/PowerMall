package com.power.mall.mapper;

import com.power.mall.model.UmsMember;
import com.power.mall.model.UmsExample;

import java.util.List;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/6/27 12:31
 */
public interface UserMemberMapper {
    int insert(UmsMember umsMember);

    List<UmsMember> selectByExample(UmsExample umsExample);

    List<UmsMember> list();
}
