package micro.rpc.sdk.config;

/**
 * @author：HeHongyi
 * @date: 2023/9/13
 * @description: rpc 注册中心配置
 */
public class RpcRegistry {
    private String ip;

    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
