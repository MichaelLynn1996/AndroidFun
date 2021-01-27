/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.net.interceptor
 * @ClassName: CacheInterceptor
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/22 22:32
 */
package cn.mlynn.androidfun.net.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.utils.NetWorkUtil;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Response resp;
        Request req;
        if (NetWorkUtil.isNetworkConnected(APP.getAppContext())) {
            //有网络,检查10秒内的缓存
            req = chain.request()
                    .newBuilder()
                    .cacheControl(new CacheControl
                            .Builder()
                            .maxAge(10, TimeUnit.SECONDS)
                            .build())
                    .build();
        } else {
            //无网络,检查30天内的缓存,即使是过期的缓存
            req = chain.request().newBuilder()
                    .cacheControl(new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(30, TimeUnit.DAYS)
                            .build())
                    .build();
        }
        resp = chain.proceed(req);
        return resp.newBuilder().build();
    }
}
