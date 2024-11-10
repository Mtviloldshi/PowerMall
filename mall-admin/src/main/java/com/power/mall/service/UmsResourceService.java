package com.power.mall.service;

import com.power.mall.model.UmsResource;
import com.power.mall.model.UmsResourceCategory;

import java.util.List;

public interface UmsResourceService {

    List<UmsResource> list(String keyword, Integer pageSize, Integer pageNum);

    List<UmsResource> listAll();

}
