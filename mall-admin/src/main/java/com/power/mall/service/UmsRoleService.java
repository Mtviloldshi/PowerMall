package com.power.mall.service;

import com.power.mall.model.UmsMenu;
import com.power.mall.model.UmsRole;

import java.util.List;

public interface UmsRoleService {
    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> getRoleList(Long adminId);

    List<UmsRole> listAll();
}
