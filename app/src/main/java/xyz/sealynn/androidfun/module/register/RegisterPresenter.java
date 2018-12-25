package xyz.sealynn.androidfun.module.register;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/25 18:10
 * <p>
 * Email：sealynndev@gmail.com
 */
public class RegisterPresenter extends BasePresenterImpl implements RegisterContract.Presenter {

    private final RegisterContract.View mView;

    RegisterPresenter(RegisterContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
