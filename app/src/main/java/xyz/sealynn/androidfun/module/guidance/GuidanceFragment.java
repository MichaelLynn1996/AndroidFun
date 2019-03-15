package xyz.sealynn.androidfun.module.guidance;

import android.os.Bundle;
import android.view.View;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 20:10
 * <p>
 * Email：sealynndev@gmail.com
 */
public class GuidanceFragment extends BaseFragment<GuidanceContract.Presenter> implements GuidanceContract.View {

    @Override
    protected GuidanceContract.Presenter createPresenter() {
        return new GuidancePresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_guidance;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initEvent() {

    }
}
