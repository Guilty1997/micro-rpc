package org.micro.test.controller;


import org.micro.server.annotation.MicroRpcDiscover;
import org.micro.test.MicroRpcTest;
import org.micro.test.entiy.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author czl
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/6/14
 */
@RestController
public class MicroRpcTestController {

    // 调用远程服务
    @MicroRpcDiscover()
    private MicroRpcTest easyRpcTest;

    // 调用本地
//    @Resource
//    private EasyRpcTest localEasyRpcTest;

    // 无传参 无返回测试
    @GetMapping("/test")
    public String test() {
        easyRpcTest.test();
        return "ok";
    }

    // POJO传参 简单返回测试
    @GetMapping("/test1")
    public String test1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("");
        userInfo.setAge(0);
        userInfo.setHobby("");
        userInfo.setAddress("");
        userInfo.setWork("");
        userInfo.setSex(0);
        userInfo.setIdCard("");
        userInfo.setPhone("");
        return easyRpcTest.test1(userInfo);
    }

    // 简单传参 POJO返回测试
    @GetMapping("/test2")
    UserInfo test2(Integer id) {
        return easyRpcTest.test2(id);
    }

    // 无传参 POJO_LIST返回测试
    @GetMapping("/test3")
    List<UserInfo> test3() {
        return easyRpcTest.test3();
    }
}
