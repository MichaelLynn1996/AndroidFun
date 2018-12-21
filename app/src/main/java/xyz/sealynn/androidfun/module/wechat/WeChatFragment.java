package xyz.sealynn.androidfun.module.wechat;

import android.os.Bundle;
import android.view.View;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseLazyLoadFragment;

/**
 * Created by SeaLynn0 on 2018/12/10 19:42
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WeChatFragment extends BaseLazyLoadFragment<WeChatContract.Presenter> implements WeChatContract.View {
    @Override
    protected void initViews(View rootView) {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected WeChatContract.Presenter createPresenter() {
        return new WeChatPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragement_wechat;
    }

    @Override
    protected void initEvent() {

    }
}
