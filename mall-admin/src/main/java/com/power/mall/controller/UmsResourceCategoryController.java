package com.power.mall.controller;

import com.power.mall.common.api.CommonResult;
import com.power.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {

    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @GetMapping("/listAll")
    public CommonResult listAll(){
        return CommonResult.success(resourceCategoryService.listAll());
    }
}
