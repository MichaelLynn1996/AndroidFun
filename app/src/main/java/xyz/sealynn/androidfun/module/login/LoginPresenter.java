package xyz.sealynn.androidfun.module.login;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class LoginPresenter extends BasePresenterImpl implements LoginContract.Presenter {

    private final LoginContract.View mView;
    public LoginPresenter(LoginContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
