package com.power.mall.dao;

import com.power.mall.model.UmsMenu;
import com.power.mall.model.UmsRole;

import java.util.List;

public interface UmsRoleDao {
    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> getRoleList(Long adminId);
}
