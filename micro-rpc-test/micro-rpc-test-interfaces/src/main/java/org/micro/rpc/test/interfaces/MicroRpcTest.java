package org.micro.rpc.test.interfaces;


import org.micro.rpc.test.interfaces.entiy.UserInfo;

import java.util.List;

public interface MicroRpcTest {

    void test();

    String test1(UserInfo userInfo);

    UserInfo test2(Integer id);

    List<UserInfo> test3();
}
