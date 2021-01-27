package cn.mlynn.androidfun.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import cn.mlynn.androidfun.base.Constants;
import cn.mlynn.androidfun.model.wan.Result;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.utils
 * @ClassName: GsonUtil
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/29 0:03
 */
public class GsonUtil {

    private GsonUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
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
