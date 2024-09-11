package com.power.mall.controller;

import com.power.mall.common.api.CommonResult;
import com.power.mall.dto.UserLoginDTO;
import com.power.mall.dto.UserRegisterDTO;
import com.power.mall.model.UmsAdmin;
import com.power.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommonResult list(){
        List<UmsAdmin> list =userAdminService.list();
        return CommonResult.success(list);
    }

}
