package com.power.mall.service;

import com.power.mall.common.api.CommonResult;
import com.power.mall.dto.UmsMenuTreeListDTO;
import com.power.mall.model.UmsMenu;

import java.util.List;

public interface UmsMenuService {

    List<UmsMenuTreeListDTO> treeList();
}
