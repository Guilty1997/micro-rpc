package micro.rpc.common.register;

/**
 * @author：HeHongyi
 * @date: 2023/9/14
 * @description: 注册中心服务数据
 */
public class ServiceRegisterData {

    /**
     * bean对象名称
     */
    private String beanName;

    /**
     * 接口类
     */
    private String interfaces;

    /**
     * 版本
     */
    private String version;

    public ServiceRegisterData(String beanName, String interfaces, String version) {
        this.beanName = beanName;
        this.interfaces = interfaces;
        this.version = version;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
