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

    /**
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> cast(Object obj) {
        return (Result<T>) obj;
    }

    /**
     * @param ctx
     * @param key
     * @param result
     */
    public static void putResultBean(Context ctx, String key, Result result) {
        ctx.getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .edit()
                .putString(key, toJson(result))
                .apply();
    }

    public static String toJson(Result result) {
        return new Gson().toJson(result);
    }

    /**
     * @param ctx
     * @param key
     * @param typeOfData
     * @param <T>
     * @return
     */
    public static <T> Result<T> getResultBean(Context ctx, String key, final Type typeOfData) {
        String json = ctx
                .getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .getString(key, "");
        return toBean(json, typeOfData);
    }

    public static <T> Result<T> toBean(String json, final Type typeOfData) {
        return new Gson().fromJson(json, typeOfData);
    }
}
