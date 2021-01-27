/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.net
 * @ClassName: NetWorkModule
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/22 0:19
 */
package cn.mlynn.androidfun.net;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.BuildConfig;
import cn.mlynn.androidfun.net.interceptor.CacheInterceptor;
import cn.mlynn.androidfun.net.interceptor.CacheNetWorkInterceptor;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.guava.GuavaCallAdapterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetWorkModule {

    private static final int DEFAULT_TIME_OUT = 10;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 20;
    private static final int DEFAULT_WRITE_TIME_OUT = 20;

    private static final int cacheSize = 50 * 1024 * 1024; // 10 MiB

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
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
        File httpCacheDirectory = new File(APP.getAppContext().getCacheDir(), "okHttpCache");
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.cache(cache);
//        CacheCont
        builder.addInterceptor(new CacheInterceptor());
        builder.addNetworkInterceptor(new CacheNetWorkInterceptor());

        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
//        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(RequestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public RequestApi provideRequestApi(Retrofit retrofit) {
        return retrofit.create(RequestApi.class);
    }
}
