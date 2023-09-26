package org.micro.server.config;

/**
 * @author：HeHongyi
 * @date: 2023/9/13
 * @description: rpc 应用程序配置
 */
public class RpcApplication {
    private String name;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
