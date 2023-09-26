package org.micro.server.config;

/**
 * @author：HeHongyi
 * @date: 2023/9/13
 * @description:
 */
public class RpcProtocol {
    private String name;
    private int port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
