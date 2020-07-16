/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.home2
 * @ClassName: HomeFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/29 11:18
 */
package cn.mlynn.androidfun.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.orhanobut.logger.Logger;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.recycler.adapter.HomeBannerAdapter;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.FragmentHomeBinding;
import cn.mlynn.androidfun.listener.AppBarStateChangeListener;
import cn.mlynn.androidfun.model.wan.Banner;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {

    static final String VIEW_MODEL_KEY = HomeFragment.class.getSimpleName();

    private com.youth.banner.Banner<Banner, HomeBannerAdapter> banner;

    private HomeBannerAdapter bannerAdapter;

    private List<Banner> banners = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        initToolbar();
        initBanner();
        initTabsAndPager();
    }

    private void initToolbar() {
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle(R.string.home);
                getBinding().appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                    @Override
                    public void onStateChanged(AppBarLayout appBarLayout, State state) {
                        if (state == State.EXPANDED) {
                            actionBar.setDisplayShowTitleEnabled(false);
                            //展开状态

                        } else if (state == State.COLLAPSED) {
                            actionBar.setDisplayShowTitleEnabled(true);
                            //折叠状态

                        } else {
                            actionBar.setDisplayShowTitleEnabled(false);
                            //中间状态
                        }
                    }
                });
            }
        }
    }

    private void initBanner() {
        banner = getBinding().getRoot().findViewById(R.id.banner);
        bannerAdapter = new HomeBannerAdapter(banners);
        banner.addBannerLifecycleObserver(HomeFragment.this)//添加生命周期观察者
                .setAdapter(bannerAdapter)
                .setIndicator(new CircleIndicator(getBinding().getRoot().getContext()))
                .start();
        getBinding().banner.getAdapter().notifyDataSetChanged();
        //  对Banner数据的监听
        getViewModel().getBanner().observe(getViewLifecycleOwner(), banners -> {
//            homeBannerAdapter.setBannerData(banners);
            Logger.d(banners);
            this.banners.clear();
            this.banners.addAll(banners);
            if (bannerAdapter != null)
                bannerAdapter.notifyDataSetChanged();
        });
    }

    private void initTabsAndPager() {
        //  TabLayout联动ViewPager2
        getBinding().pagers.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if (position == 0)
                    return new TopFragment();
                else return new LatestProjectFragment();
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });
        //  Tabs的数据
        String[] titles = {getString(R.string.latest_article),
                getString(R.string.latest_project)};
//        int[] icon = {R.drawable.ic_action_home, R.drawable.project, R.drawable.guangchang, R.drawable.wenda};
        new TabLayoutMediator(getBinding().tabs,
                getBinding().pagers,
                (tab, position) -> tab.setText(titles[position])).attach();
    }

    @Override
    protected FragmentHomeBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void dismissLoading() {

    }

    @Override
    protected void startLoading() {

    }

    @Override
    protected HomeViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(VIEW_MODEL_KEY, HomeViewModel.class);
    }

    @Override
    public void onDestroyView() {
        banner = null;
        bannerAdapter = null;
        super.onDestroyView();
    }
}
