package com.power.mall.dao;

import com.power.mall.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleDao {
    int insertList(@Param("list") List<UmsAdminRoleRelation> umsAdminRoleRelations);

    List<UmsMenu> getMenuList(Long adminId);

    List<UmsRole> getRoleList(Long adminId);

    List<UmsRoleResourceRelation> getListResource(Long id);
}
