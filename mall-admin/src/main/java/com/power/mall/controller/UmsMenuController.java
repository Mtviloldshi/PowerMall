package com.power.mall.controller;

import com.power.mall.common.api.CommonResult;
import com.power.mall.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/14 6:27
 */
@RestController
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    @GetMapping("/treeList")
    public CommonResult treeList(){
        return CommonResult.success(umsMenuService.treeList());
    }
}
