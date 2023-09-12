package micro.rpc.common.register;

/**
 * @author：HeHongyi
 * @date: 2023/9/8
 * @description: service对象
 */
public class ServiceObject {
    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务类
     */
    private Class<?> serviceClass;

    /**
     * 版本
     */
    private String version;

    public ServiceObject(String name, Class<?> serviceClass, String version) {
        this.name = name;
        this.serviceClass = serviceClass;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
