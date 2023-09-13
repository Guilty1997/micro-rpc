package org.micro.server.socket.server.handler;


/**
 * @author：HeHongyi
 * @date: 2023/7/4
 * @description:
 */
public class RpcRequest {

    /**
     * 客户端需要同步获取响应结果 每个消息需要唯一标识
     */
    private String requestId;
    /**
     * 需要调用远程的Bean的别名
     */
    private String beanRef;

    /**
     * 需要调用远程的接口
     */
    private String interfaces;
    /**
     * 需要调用的方法
     */
    private String methodName;
    /**
     * 需要调用的方法的入参属性
     */
    private Class[] paramTypes;
    /**
     * 需要调用的方法的入参
     */
    private Object[] args;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBeanRef() {
        return beanRef;
    }

    public void setBeanRef(String beanRef) {
        this.beanRef = beanRef;
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
