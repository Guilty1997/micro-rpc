package org.micro.rpc.test.consumer.controller;


import micro.rpc.common.Result;

import org.micro.rpc.test.interfaces.MicroRpcTest;
import org.micro.rpc.test.interfaces.entiy.UserInfo;
import org.micro.server.annotation.MicroRpcDiscover;
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
    @MicroRpcDiscover(host = "127.0.0.1:20880")
    private MicroRpcTest microRpcTest;

    // 调用本地
//    @Resource
//    private EasyRpcTest localEasyRpcTest;

    // 无传参 无返回测试
    @GetMapping("/test")
    public Result<String> test() {
        microRpcTest.test();

//        return new Result<>(200, "success", "success");
        return Result.success("success");
    }

    // POJO传参 简单返回测试
    @GetMapping("/test1")
    public Result<String> test1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("");
        userInfo.setAge(0);
        userInfo.setHobby("");
        userInfo.setAddress("");
        userInfo.setWork("");
        userInfo.setSex(0);
        userInfo.setIdCard("");
        userInfo.setPhone("");
        return Result.success(microRpcTest.test1(userInfo));
    }

    // 简单传参 POJO返回测试
    @GetMapping("/test2")
    UserInfo test2(Integer id) {
        return microRpcTest.test2(id);
    }

    // 无传参 POJO_LIST返回测试
    @GetMapping("/test3")
    List<UserInfo> test3() {
        return microRpcTest.test3();
    }
}
