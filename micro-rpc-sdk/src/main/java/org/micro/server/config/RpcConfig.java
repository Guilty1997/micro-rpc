package org.micro.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author：HeHongyi
 * @date: 2023/9/12
 * @description: RPC配置配置中心（可以通过yml配置）
 */
@ConfigurationProperties(prefix = "micro-rpc")
public class RpcConfig {
    private RpcRegistry registry = new RpcRegistry();

    private RpcApplication application = new RpcApplication();

    private RpcProtocol protocol = new RpcProtocol();

    public RpcRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(RpcRegistry registry) {
        this.registry = registry;
    }

    public RpcApplication getApplication() {
        return application;
    }

    public void setApplication(RpcApplication application) {
        this.application = application;
    }

    public RpcProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(RpcProtocol protocol) {
        this.protocol = protocol;
    }
}
