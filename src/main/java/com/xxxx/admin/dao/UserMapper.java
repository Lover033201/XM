package com.xxxx.admin.dao;

import com.xxxx.admin.base.BaseMapper;
import com.xxxx.admin.vo.User;

public interface UserMapper extends BaseMapper<User,Integer> {

    //通过用户名查询用户记录，返回用户对象
    User queryUserByName(String userName);
}