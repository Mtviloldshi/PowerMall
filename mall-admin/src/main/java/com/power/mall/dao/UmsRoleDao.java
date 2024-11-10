package com.power.mall.dao;

import com.power.mall.model.UmsAdminRoleRelation;
import com.power.mall.model.UmsAdminRoleRelationExample;
import com.power.mall.model.UmsMenu;
import com.power.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleDao {
    int insertList(@Param("list") List<UmsAdminRoleRelation> umsAdminRoleRelations);

    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> getRoleList(Long adminId);
}
