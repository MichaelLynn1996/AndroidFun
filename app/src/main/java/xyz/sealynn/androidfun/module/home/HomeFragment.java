package xyz.sealynn.androidfun.module.home;

import android.os.Bundle;
import android.view.View;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseLazyLoadFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 19:56
 * <p>
 * Email：sealynndev@gmail.com
 */
public class HomeFragment extends BaseLazyLoadFragment<HomeContract.Presenter> implements HomeContract.View {
    @Override
    protected void initViews(View rootView) {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEvent() {

    }
}
