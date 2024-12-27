package com.power.mall.controller;


import com.power.mall.common.api.CommonPage;
import com.power.mall.common.api.CommonResult;
import com.power.mall.model.UmsResource;
import com.power.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService resourceService;


    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "keyword", required = false)String keyword,
                             @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum
    ){
        List<UmsResource> list = resourceService.list(keyword,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @GetMapping("/listAll")
    public CommonResult listAll(){
        return CommonResult.success(resourceService.listAll());
    }
}
