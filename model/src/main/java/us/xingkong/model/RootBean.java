package us.xingkong.model;

/**
 * Created by SeaLynn0 on 2018/9/5 11:27
 * <p>
 * Email：sealynndev@gmail.com
 */
public class RootBean<T> {

    private T date;

    private int errorCode;

    private String errorMsg;

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
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
