package org.micro.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

//@EnableEasyRpc
@SpringBootApplication
public class MicroRpcTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroRpcTestApplication.class, args);
    }

}
