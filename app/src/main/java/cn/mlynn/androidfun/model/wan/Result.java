package cn.mlynn.androidfun.model.wan;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import cn.mlynn.androidfun.base.Constants;

/**
 * Created by SeaLynn0 on 2018/12/5 2:31
 * <p>
 * Email：sealynndev@gmail.com
 */
public class Result<T> {

    /**
     * data : {"chapterTops":[],"collectIds":[2683],"email":"","icon":"","id":3768,"password":"","token":"","type":0,"username":"SeaLynn0"}
     * errorCode : 0
     * errorMsg :
     */

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
