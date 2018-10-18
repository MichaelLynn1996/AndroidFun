package xyz.sealynn.androidfun.module.web;

import xyz.sealynn.androidfun.base.BasePresenterImpl;
import xyz.sealynn.androidfun.base.BasePresenter;

/**
 * Created by SeaLynn0 on 2018/9/5 0:33
 * <p>
 * Email：sealynndev@gmail.com
 */
class WebPresenter extends BasePresenterImpl implements WebContract.Presenter {

    private final WebContract.View mView;

    WebPresenter(WebContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
