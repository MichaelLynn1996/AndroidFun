package xyz.sealynn.androidfun.module.wx;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.model.wxarticle.WxChapter;
import xyz.sealynn.androidfun.module.BaseMainFragment;

/**
 * Created by SeaLynn0 on 2018/12/10 19:42
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WxFragment extends BaseMainFragment<WxContract.Presenter> implements WxContract.View {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pagers)
    ViewPager2 pager;
//    @BindView(R.id.refresh_layout)
//    SwipeRefreshLayout refresh;

//    private WxPresenter viewModel;

    @Override
    protected WxContract.Presenter createPresenter() {
        return new WxPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragement_wechat;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getPresenter().getWxChapter();
    }

    @Override
    protected int getTitle() {
        return R.string.wechat;
    }

    @Override
    protected void initEvent() {
//        refresh.setOnRefreshListener(() -> getPresenter().getWxChapter());
    }

    @Override
    public void setLoading(Boolean isLoading) {
//        refresh.setRefreshing(isLoading);
    }

    @Override
    public void setTags(List<WxChapter> chapters) {
        if (tabs.getVisibility() == View.GONE)
            tabs.setVisibility(View.VISIBLE);
        if (pager.getVisibility() == View.GONE)
            pager.setVisibility(View.VISIBLE);
        pager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return WxChapterListFragment.newInstance(chapters.get(position));
            }

            @Override
            public int getItemCount() {
                return chapters.size();
            }
        });
        new TabLayoutMediator(tabs, pager, (tab, position) -> tab.setText(chapters.get(position).getName())).attach();
    }
}
