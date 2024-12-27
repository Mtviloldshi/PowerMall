package com.power.mall.service;

import com.power.mall.model.UmsMenu;
import com.power.mall.model.UmsRole;
import com.power.mall.model.UmsRoleResourceRelation;

import java.util.List;

public interface UmsRoleService {
    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> getRoleList(Long adminId);

    List<UmsRole> listAll();

    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);


    List<UmsRoleResourceRelation> getListResource(Long id);
}
