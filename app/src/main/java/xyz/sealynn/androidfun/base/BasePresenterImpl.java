package xyz.sealynn.androidfun.base;

import com.orhanobut.logger.Logger;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xyz.sealynn.androidfun.net.OnHttpCallBackListener;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by SeaLynn0 on 2018/9/4 23:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>, OnHttpCallBackListener {

    private CompositeDisposable compositeDisposable;

    private V mView;

    public BasePresenterImpl() {
    }

    protected BasePresenterImpl(V view) {
        this.mView = view;
        compositeDisposable = new CompositeDisposable();
    }

    protected V getView() {
        return mView;
    }

    /**
     * @param observable
     * @param what
     * @param <T>
     */
    public <T> void domine(Observable<T> observable, final int what) {

//网络处理使用的是RxJava+Retrofit的组合 通过回调进行获取网络数据，这样做的好处是对于请求的结果或者请求出现异常的处理可以全局设置也可以单个请求设置，灵活度高
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull T t) {
                onResponse(t, what);
                Logger.d(t);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                onErrors(e, what);
            }

            @Override
            public void onComplete() {
                onCompletes(what);
            }

        });
    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }


    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onAny(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onErrors(Throwable e, int what) {
        ToastUtils.longToast(getView().getContext(), "似乎有点问题！");
        e.printStackTrace();
    }

    @Override
    public void onCompletes(int what) {

    }
}
