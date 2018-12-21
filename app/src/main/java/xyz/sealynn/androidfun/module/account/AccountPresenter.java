package xyz.sealynn.androidfun.module.account;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/10 20:47
 * <p>
 * Email：sealynndev@gmail.com
 */
public class AccountPresenter extends BasePresenterImpl implements AccountContract.Presenter {

    private final AccountContract.View mView;
    public AccountPresenter(AccountContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
