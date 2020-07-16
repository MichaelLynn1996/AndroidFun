package cn.mlynn.androidfun.net;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.guava.GuavaCallAdapterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.BuildConfig;
import cn.mlynn.androidfun.utils.NetWorkUtil;

/**
 * Created by SeaLynn0 on 2018/12/5 2:36
 * <p>
 * Email：sealynndev@gmail.com
 */
public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;

    private static final int DEFAULT_TIME_OUT = 10;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 20;
    private static final int DEFAULT_WRITE_TIME_OUT = 20;

    private static final int cacheSize = 50 * 1024 * 1024; // 10 MiB

    private RetrofitManager() {
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }


    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

//        builder.addInterceptor(new SaveCookiesInterceptor(APP.getAppContext()))
//                .addInterceptor(new AddCookiesInterceptor(APP.getAppContext()));
        //添加retrofit日志打印
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor);
        }

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(APP.getAppContext()));
        builder.cookieJar(cookieJar);

        //setup cache
//        File httpCacheDirectory = new File(APP.getAppContext().getCacheDir(), "okHttpCache");
//        Cache cache = new Cache(httpCacheDirectory, cacheSize);
//        builder.cache(cache);
//        builder.addInterceptor(cacheControlInterceptor);

        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
//        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(RequestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public RequestApi createReq() {
        return mRetrofit.create(RequestApi.class);
    }

    //    public <T> T createReq(Class<T> reqServer) {
//        return mRetrofit.create(reqServer);
//    }
//这里是设置拦截器，供下面的函数调用，辅助作用。
    private static final Interceptor cacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected(APP.getAppContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected(APP.getAppContext())) {
                // 有网络时 设置缓存为默认值
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时 设置超时为1周
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

}
