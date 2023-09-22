package micro.rpc.common;

import micro.rpc.common.client.RpcRequest;
import micro.rpc.common.server.RpcResponse;

/**
 * @author：HeHongyi
 * @date: 2023/9/22
 * @description: Rpc枚举类
 */
public enum RpcEnum {

    /**
     * 1:请求
     * 2:响应
     */
    REQUEST((byte) 1, RpcRequest.class),
    RESPONSE((byte) 2, RpcResponse.class);
    private final int code;
    private final Class aClass;

    public int getCode() {
        return code;
    }

    public Class getaClass() {
        return aClass;
    }

    RpcEnum(byte code, Class aClass) {
        this.code = code;
        this.aClass = aClass;
    }

    //通过code获取枚举值
    public static RpcEnum getByCode(byte code) {
        for (RpcEnum v : values()) {
            if (v.code == code) {
                return v;
            }
        }
        return null;
    }

}
