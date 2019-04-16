package xyz.sealynn.androidfun.module.wechat;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.adapter.WeChatChapterPagerAdapter;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.model.WxChapter;

/**
 * Created by SeaLynn0 on 2018/12/10 19:42
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WeChatFragment extends BaseFragment<WeChatContract.Presenter> implements WeChatContract.View {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pagers)
    ViewPager pager;
//    @BindView(R.id.refresh_layout)
//    SwipeRefreshLayout refresh;

//    private WeChatPresenter viewModel;

    @Override
    protected WeChatContract.Presenter createPresenter() {
        return new WeChatPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
        getPresenter().getWeChatChapter();
//        viewModel = ViewModelProviders.of(this).get(WeChatPresenter.class);
//        Logger.d(viewModel);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragement_wechat;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {
//        viewModel.getmWeChatChapters().observe(this, new Observer<List<WxChapter>>() {
//            @Override
//            public void onChanged(List<WxChapter> weChatChapters) {
//                Logger.d("onChanged");
//            }
//        });
        tabs.setupWithViewPager(pager);
    }

    @Override
    protected void initEvent() {
//        refresh.setOnRefreshListener(() -> getPresenter().getWeChatChapter());
    }

    @Override
    public void setLoading(Boolean isLoading) {
//        refresh.setRefreshing(isLoading);
    }

    @Override
    public void setTags(List<WxChapter> chapters) {
        if (tabs.getVisibility() == View.GONE)
            tabs.setVisibility(View.VISIBLE);
        for (WxChapter chapter : chapters) {
            tabs.addTab(tabs.newTab().setText(chapter.getName()));
        }
        pager.setAdapter(new WeChatChapterPagerAdapter(getFragmentManager(),chapters));
        tabs.setupWithViewPager(pager);
    }
}
