package com.xxxx.admin.service;

import com.xxxx.admin.base.BaseService;
import com.xxxx.admin.dao.UserMapper;
import com.xxxx.admin.model.UserModel;
import com.xxxx.admin.utils.AssertUtil;
import com.xxxx.admin.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;
    /**
     *用户登录
     * 1. 参数判断，判断用户姓名、用户密码非空弄
     *     如果参数为空，抛出异常（异常被控制层捕获并处理）
     * 2. 调用数据访问层，通过用户名查询用户记录，返回用户对象
     * 3. 判断用户对象是否为空
     *     如果对象为空，抛出异常（异常被控制层捕获并处理）
     * 4. 判断密码是否正确，比较客户端传递的用户密码与数据库中查询的用户对象中的用户密
     *     如果密码不相等，抛出异常（异常被控制层捕获并处理）
     * 5. 如果密码正确，登录成功
     */
    public UserModel userLogin(String userName, String password){
        //判断姓名，密码非空
        checkLoginParams(userName,password);

        //查询判断用户非空
        User user=userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user==null,"用户姓名不存在！");
        //判断密码正确
        checkPassword(password,user.getPassword());
        //返回构建对象
        return buildUserInfo(user);

    }

    private void checkPassword(String password, String password1) {
        AssertUtil.isTrue(!password.equals(password1),"用户密码不正确！");
    }

    private void checkLoginParams(String userName, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户姓名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(password),"用户密码不能为空！");
    }

    private UserModel buildUserInfo(User user){
        UserModel userModel=new UserModel();
        userModel.setUserId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }
}
