/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.net.interceptor
 * @ClassName: CacheControlInterceptor
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/22 21:17
 */
package cn.mlynn.androidfun.net.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheNetWorkInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        //无缓存,进行缓存
        return chain.proceed(chain.request()).newBuilder()
                .removeHeader("Pragma")
                //对请求进行最大60秒的缓存
                .addHeader("Cache-Control", "max-age=60")
                .build();
    }
}
