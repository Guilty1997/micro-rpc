package org.micro.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author：HeHongyi
 * @date: 2023/9/12
 * @description: RPC配置配置中心（可以通过yml配置）
 */
@ConfigurationProperties(prefix = "micro-rpc")
public class RpcConfig {

    private int port = 11111;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
