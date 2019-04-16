package xyz.sealynn.androidfun.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.sealynn.androidfun.APP;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.net.interceptor.AddCookiesInterceptor;
import xyz.sealynn.androidfun.net.interceptor.SaveCookiesInterceptor;
import xyz.sealynn.androidfun.utils.AppUtils;

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

        builder.addInterceptor(new SaveCookiesInterceptor(APP.getAppContext()))
                .addInterceptor(new AddCookiesInterceptor(APP.getAppContext()));
        //添加retrofit日志打印
        if (AppUtils.isApkInDebug(APP.getAppContext())) {
            builder.addInterceptor(interceptor);
        }

        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
//        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(RequestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public RequestApi createReq() {
        return mRetrofit.create(RequestApi.class);
    }

//    public <T> T createReq(Class<T> reqServer) {
//        return mRetrofit.create(reqServer);
//    }

}
