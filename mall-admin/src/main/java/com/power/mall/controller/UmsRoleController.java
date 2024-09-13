package com.power.mall.controller;

import com.power.mall.common.api.CommonResult;
import com.power.mall.model.UmsRole;
import com.power.mall.service.UmsAdminService;
import com.power.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name 未命名
 * @Author SCH
 * @Date 2024/9/13 18:07
 */
@RestController
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;
    @Autowired
    private UmsAdminService adminService;
    @GetMapping("/listAll")
    public CommonResult listAll(){
        return CommonResult.success(roleService.listAll());
    }
}
