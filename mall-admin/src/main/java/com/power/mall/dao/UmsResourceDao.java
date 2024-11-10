package com.power.mall.dao;

import com.power.mall.model.UmsMenu;
import com.power.mall.model.UmsResource;

import java.util.List;

public interface UmsResourceDao {

    List<UmsResource> getResourceListByAdminId(Long adminId);

}
