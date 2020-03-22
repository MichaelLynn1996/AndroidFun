package xyz.sealynn.androidfun.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.databinding.FragmentHomeBinding;
import xyz.sealynn.androidfun.module.BaseMainFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 19:56
 * <p>
 * Email：sealynndev@gmail.com
 */
public class HomeFragment extends BaseMainFragment<HomeContract.Presenter, FragmentHomeBinding> implements HomeContract.View {

//    @BindView(R.id.refresh)
//    SwipeRefreshLayout refreshLayout;
//    @BindView(R.id.home_list)
//    RecyclerView homelist;

    @Override
    protected FragmentHomeBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getTitle() {
        return R.string.home;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void loading(Boolean isLoading) {
//        refreshLayout.setRefreshing(isLoading);
    }
}
