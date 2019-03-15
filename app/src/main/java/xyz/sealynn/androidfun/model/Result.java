package xyz.sealynn.androidfun.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.room.Entity;
import xyz.sealynn.androidfun.base.Constants;

/**
 * Created by SeaLynn0 on 2018/12/5 2:31
 * <p>
 * Email：sealynndev@gmail.com
 */
@Entity
public class Result<T> {


    /**
     * data : {"chapterTops":[],"collectIds":[2683],"email":"","icon":"","id":3768,"password":"","token":"","type":0,"username":"SeaLynn0"}
     * errorCode : 0
     * errorMsg :
     */

    private T data;
    private int errorCode;
    private String errorMsg;

//    /**
//     * @param obj
//     * @param <P>
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <P> P cast(Object obj) {
//        return (P) obj;
//    }

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
        String json = new Gson().toJson(result);
        ctx.getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .edit()
                .putString(key, json)
                .apply();
    }

    public static <T> Result<T> getResultBean(Context ctx, String key, final Type typeOfData) {
        Gson gson = new Gson();
        String json = ctx
                .getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .getString(key, "");
        return gson.fromJson(json, typeOfData);
    }
}
