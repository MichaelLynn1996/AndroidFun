/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.base2
 * @ClassName: BaseRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/10 15:15
 */
package cn.mlynn.androidfun.base;

import androidx.lifecycle.Lifecycle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.net.RequestApi;
import cn.mlynn.androidfun.utils.ToastUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BaseRepository {

    private Executor executor = Executors.newSingleThreadExecutor();

    private RequestApi requestApi;

    public BaseRepository(RequestApi requestApi) {
        this.requestApi = requestApi;
    }

    /**
     * @param observable Retrofit返回的对象
     * @param <T> 数据类型
     */
    protected <T> void query(Lifecycle lifecycle, Observable<T> observable, QueryCallBack<T> callBack) {
//网络处理使用的是RxJava+Retrofit的组合 通过回调进行获取网络数据，这样做的好处是对于请求的结果或者请求出现异常的处理可以全局设置也可以单个请求设置，灵活度高
        if (lifecycle != null)
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                    .subscribe(new Observer<T>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull T t) {
                            callBack.onSuccess(t);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            callBack.onFailure(e);
                        }

                        @Override
                        public void onComplete() {
                        }

                    });
    }

    public Executor getExecutor() {
        return executor;
    }

    public interface QueryCallBack<T> {

        void onSuccess(T entity);

        void onFailure(@NonNull Throwable e);
    }

    public RequestApi getRequestApi() {
        return requestApi;
    }
}
