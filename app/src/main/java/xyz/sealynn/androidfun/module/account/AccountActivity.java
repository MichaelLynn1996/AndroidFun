package xyz.sealynn.androidfun.module.account;

import android.os.Bundle;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;

/**
 * Created by SeaLynn0 on 2018/12/10 20:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class AccountActivity extends BaseActivity<AccountContract.Presenter> implements AccountContract.View {


    @Override
    protected AccountContract.Presenter createPresenter() {
        return new AccountPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_acount;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {

    }
}
