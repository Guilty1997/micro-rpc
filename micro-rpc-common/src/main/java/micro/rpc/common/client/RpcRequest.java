package micro.rpc.common.client;

/**
 * @author：HeHongyi
 * @date: 2023/9/21
 * @description: 客户端请求
 */
public class RpcRequest {
    /**
     * 客户端需要同步获取响应结果 每个消息需要唯一标识
     */
    private String requestId;
    /**
     * 需要调用远程的Bean的别名
     */
    private String beanName;       //别名

    /**
     * 需要调用远程的接口
     */
    private String interfaces;
    /**
     * 需要调用的方法
     */
    private String methodName;  //方法
    /**
     * 需要调用的方法的入参属性
     */
    private Class[] paramTypes; //入参属性
    /**
     * 需要调用的方法的入参
     */
    private Object[] args;      //入参


    public RpcRequest() {
    }

    public RpcRequest(String requestId, String beanName, String interfaces, String methodName, Class[] paramTypes, Object[] args) {
        this.requestId = requestId;
        this.beanName = beanName;
        this.interfaces = interfaces;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.args = args;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
