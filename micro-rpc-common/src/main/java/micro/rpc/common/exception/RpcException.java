package micro.rpc.common.exception;

/**
 * @author：HeHongyi
 * @date: 2023/9/28
 * @description: RPC异常
 */
public class RpcException extends RuntimeException {
    public RpcException(String message) {
        super(message);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
}
