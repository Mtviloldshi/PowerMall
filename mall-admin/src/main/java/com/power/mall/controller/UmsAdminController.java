package com.power.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.power.mall.common.api.CommonPage;
import com.power.mall.common.api.CommonResult;
import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.model.UmsAdmin;
import com.power.mall.model.UmsRole;
import com.power.mall.service.UmsAdminService;
import com.power.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SpinnerUI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Name 后台用户管理
 * @Author SCH
 * @Date 2024/6/27 12:02
 */
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService userAdminService;
    @Autowired
    private UmsRoleService roleService;

    @PostMapping("/register")
    //@Validated 验证参数 NotEmpty?
    public CommonResult<Boolean> register(@RequestBody UserRegisterDTO registerDTO){
        Boolean register = userAdminService.register(registerDTO);
        System.out.println(register);
        return CommonResult.success(register);
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;
 
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginDTO loginDTO){
        String token = userAdminService.login(loginDTO);
        Map<String,String> result = new HashMap<>();
        if (token == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        result.put("token",token);
        result.put("tokenHead",tokenHead);
        return CommonResult.success(result);
    }
    @GetMapping("/list")
    public CommonResult list(@RequestParam(value = "keyword",required = false)String keyword,
                             @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                             @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum
                             ){
        List<UmsAdmin> list =userAdminService.list(keyword,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @GetMapping("/role/{adminId}")
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId){
        List<UmsRole> roleList =userAdminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
    @GetMapping("info")
    public CommonResult info(Principal principal){
        if (principal == null){
            CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = userAdminService.getAdminByUsername(username);
        Map<String,Object> data = new HashMap<>();
        data.put("username",umsAdmin.getUsername());
        data.put("menus",roleService.getMenuList(umsAdmin.getId()));
        data.put("icon",umsAdmin.getIcon());
        List<UmsRole> roleList = roleService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

}
