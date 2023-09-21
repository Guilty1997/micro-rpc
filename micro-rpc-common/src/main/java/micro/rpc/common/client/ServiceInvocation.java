package micro.rpc.common.client;

/**
 * @author：HeHongyi
 * @date: 2023/9/18
 * @description: 代理类
 */
public class ServiceInvocation {
    private String interfaceName;
    private String beanRef;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getBeanRef() {
        return beanRef;
    }

    public void setBeanRef(String beanRef) {
        this.beanRef = beanRef;
    }
}
