package xyz.sealynn.androidfun.module.wxlist;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.adapter.WxListAdapter;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.model.wxarticle.WxActicle;
import xyz.sealynn.androidfun.model.wxarticle.WxChapter;

/**
 * Created by MichaelLynn on 2020/2/4 0:14
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WxListFragment extends BaseFragment<WxListContract.Presenter> implements WxListContract.View {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.list)
    RecyclerView WxList;

    private WxListAdapter adapter = new WxListAdapter();

    private static final String KEY = "chapter";

    private int page = 1;

    private WxChapter chapter;
    private List<WxActicle.DatasBean> datasBeans = new ArrayList<>();

    /**
     * fragment静态传值
     */
    public static WxListFragment newInstance(WxChapter chapter) {
        WxListFragment fragment = new WxListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, new Gson().toJson(chapter));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected WxListContract.Presenter createPresenter() {
        return new WxListPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
        if (getArguments() != null) {
            chapter = new Gson().fromJson(getArguments().getString(KEY), WxChapter.class);
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_wx_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {
        WxList.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public boolean isLoading() {
        return refreshLayout.isRefreshing();
    }

    @Override
    public void setLoading(Boolean isLoading) {
        refreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void loadDatas(List<WxActicle.DatasBean> datas) {
        datasBeans.addAll(datas);
    }
}
