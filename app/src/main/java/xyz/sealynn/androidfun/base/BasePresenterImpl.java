package xyz.sealynn.androidfun.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by SeaLynn0 on 2018/9/4 23:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public abstract class BasePresenterImpl implements BasePresenter {

    protected CompositeDisposable mSubscriptions;

    @Override
    public void onStart() {
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeDisposable();
        }
    }

    @Override
    public void onDestroy() {
        if (mSubscriptions != null) {
            mSubscriptions.dispose();
            mSubscriptions.clear();
        }
    }
}
