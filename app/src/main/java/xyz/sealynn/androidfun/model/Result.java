package xyz.sealynn.androidfun.model;

/**
 * Created by SeaLynn0 on 2018/12/5 2:31
 * <p>
 * Email：sealynndev@gmail.com
 */
public class Result<T> {

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
