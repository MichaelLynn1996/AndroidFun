package xyz.sealynn.androidfun.module.home;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 19:56
 * <p>
 * Email：sealynndev@gmail.com
 */
public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.home_list)
    RecyclerView homelist;

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
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void loading(Boolean isLoading) {
        refreshLayout.setRefreshing(isLoading);
    }
}
