package micro.rpc.test.provider.impl;


import micro.rpc.test.interfaces.MicroRpcTest;
import micro.rpc.test.interfaces.entiy.UserInfo;
import micro.rpc.sdk.annotation.MicroRpcRegister;
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
@MicroRpcRegister(beanName = "microRpcTest")
public class MicroRpcTestImpl implements MicroRpcTest {
    @Override
    public void test() {

    }

    @Override
    public String test1(UserInfo userInfo) {
        return "ok111111";
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
