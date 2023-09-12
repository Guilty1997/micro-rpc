package org.micro.test.impl;


import org.micro.server.annotation.MicroRpcRegister;
import org.micro.test.MicroRpcTest;
import org.micro.test.entiy.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author czl
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/6/14
 */
@Service
@MicroRpcRegister()
public class MicroRpcTestImpl implements MicroRpcTest {
    @Override
    public void test() {

    }

    @Override
    public String test1(UserInfo userInfo) {
        return "ok";
    }

    @Override
    public UserInfo test2(Integer id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("");
        userInfo.setAge(0);
        userInfo.setHobby("");
        userInfo.setAddress("");
        userInfo.setWork("");
        userInfo.setSex(0);
        userInfo.setIdCard("");
        userInfo.setPhone("");
        return userInfo;
    }

    @Override
    public List<UserInfo> test3() {
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setAge(0);
            userInfo.setHobby("");
            userInfo.setAddress("");
            userInfo.setWork("");
            userInfo.setSex(0);
            userInfo.setIdCard("");
            userInfo.setPhone("");
            userInfos.add(userInfo);
        }
        return userInfos;
    }
}