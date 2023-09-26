package micro.rpc.common;

/**
 * @author：HeHongyi
 * @date: 2023/9/24
 * @description:
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功！", data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
