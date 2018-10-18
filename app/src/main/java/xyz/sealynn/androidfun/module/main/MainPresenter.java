package xyz.sealynn.androidfun.module.main;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/9/5 11:43
 * <p>
 * Email：sealynndev@gmail.com
 */
public class MainPresenter extends BasePresenterImpl implements MainContract.Presenter {

    private final MainContract.View mView;

    MainPresenter(MainContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
