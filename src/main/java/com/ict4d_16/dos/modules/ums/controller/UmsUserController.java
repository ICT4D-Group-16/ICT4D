package com.ict4d_16.dos.modules.ums.controller;

import cn.hutool.core.collection.CollUtil;
import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.modules.ums.dto.UmsAdminLoginParam;
import com.ict4d_16.dos.modules.ums.dto.UmsAdminParam;
import com.ict4d_16.dos.modules.ums.dto.UmsUserVxmlRegisterParam;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import com.ict4d_16.dos.modules.ums.model.UmsRole;
import com.ict4d_16.dos.modules.ums.service.UmsAdminService;
import com.ict4d_16.dos.modules.ums.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Api(tags = "UmsUserController")
@Tag(name = "UmsUserController", description = "User Management")
@RequestMapping("/user")
public class UmsUserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("Check user whether registered by phone number. If registered, return true; otherwise, return false.")
    @RequestMapping(value = "/check_user/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Boolean> check(@PathVariable String phone) {
        UmsAdmin user = adminService.getAdminByPhone(phone);
        if (user != null) {
            return CommonResult.success(true, "User already registered.");
        } else {
            return CommonResult.success(false, "User not registered.");
        }
    }

    @ApiOperation(value = "User registration with password")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        adminService.updateRole(umsAdmin.getId(), Collections.singletonList(9L));
        umsAdmin.setPassword(null);
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "User registration with phone number and without password." +
            " Username and password will be set as phone number.")
    @RequestMapping(value = "/register_vxml", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsUserVxmlRegisterParam umsUserVxmlRegisterParam) {
        UmsAdminParam umsAdmin = new UmsAdminParam();
        umsAdmin.setPhone(umsUserVxmlRegisterParam.getPhone());
        umsAdmin.setNickName(umsUserVxmlRegisterParam.getNickName());
        umsAdmin.setUsername(umsUserVxmlRegisterParam.getPhone());
        umsAdmin.setLanguage(umsUserVxmlRegisterParam.getLanguage());
        UmsAdmin user = adminService.register(umsAdmin);
        umsAdmin.setPassword(null);
        if (user == null) {
            return CommonResult.failed();
        }
        adminService.updateRole(user.getId(), Collections.singletonList(10L));
        return CommonResult.success(user);
    }

    @ApiOperation(value = "Login and return token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("Username or password incorrect.");
        }
        UmsAdmin umsAdmin = adminService.getAdminByUsername(umsAdminLoginParam.getUsername());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("tokenHead", tokenHead);
        resultMap.put("id", umsAdmin.getId().toString());
        resultMap.put("username", umsAdmin.getUsername());
        resultMap.put("nickname", umsAdmin.getNickName());
        resultMap.put("phone", umsAdmin.getPhone());
        resultMap.put("language", umsAdmin.getLanguage());
        return CommonResult.success(resultMap);
    }


    @ApiOperation(value = "Get user information based on token")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "Get user information based on phone number")
    @RequestMapping(value = "/info/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getUserInfo(@PathVariable String phone) {
        UmsAdmin umsAdmin = adminService.getAdminByPhone(phone);
        if (umsAdmin != null) {
            return CommonResult.success(umsAdmin);
        } else {
            return CommonResult.failed("User not registered.");
        }
    }

    @ApiOperation(value = "Logout")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }
}
