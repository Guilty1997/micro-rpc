package micro.rpc.common.register;

import java.util.List;

/**
 * @author：HeHongyi
 * @date: 2023/9/14
 * @description: service实例
 */
public class ServiceInstance {

    private String ip;

    private int port;

    private List<ServiceRegisterData> registerData;

    public ServiceInstance(String ip, int port, List<ServiceRegisterData> registerData) {
        this.ip = ip;
        this.port = port;
        this.registerData = registerData;
    }

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

    public List<ServiceRegisterData> getRegisterData() {
        return registerData;
    }

    public void setRegisterData(List<ServiceRegisterData> registerData) {
        this.registerData = registerData;
    }

}
