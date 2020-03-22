package xyz.sealynn.androidfun.module.wx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.databinding.FragementWechatBinding;
import xyz.sealynn.androidfun.model.wxarticle.WxChapter;
import xyz.sealynn.androidfun.module.BaseMainFragment;

/**
 * Created by SeaLynn0 on 2018/12/10 19:42
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WxFragment extends BaseMainFragment<WxContract.Presenter, FragementWechatBinding> implements WxContract.View {

    @Override
    protected FragementWechatBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragementWechatBinding.inflate(getLayoutInflater());
    }

    @Override
    protected WxContract.Presenter createPresenter() {
        return new WxPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

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
        if (getBinding().tabs.getVisibility() == View.GONE)
            getBinding().tabs.setVisibility(View.VISIBLE);
        if (getBinding().pagers.getVisibility() == View.GONE)
            getBinding().pagers.setVisibility(View.VISIBLE);
        getBinding().pagers.setAdapter(new FragmentStateAdapter(this) {
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
        new TabLayoutMediator(getBinding().tabs, getBinding().pagers, (tab, position) -> tab.setText(chapters.get(position).getName())).attach();
    }
}
