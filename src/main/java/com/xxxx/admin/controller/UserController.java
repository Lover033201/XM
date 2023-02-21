package com.xxxx.admin.controller;

import com.xxxx.admin.base.BaseController;
import com.xxxx.admin.base.ResultInfo;
import com.xxxx.admin.model.UserModel;
import com.xxxx.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /*用户登录*/
    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String password){
        ResultInfo resultInfo=new ResultInfo();
        UserModel userModel=userService.userLogin(userName,password);
        resultInfo.setResult(userModel);
        return resultInfo;
    }
}
