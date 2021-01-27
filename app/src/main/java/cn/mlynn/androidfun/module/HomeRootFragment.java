package cn.mlynn.androidfun.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.ExBaseFragment;
import cn.mlynn.androidfun.databinding.LayoutAppbarTabPagerBinding;
import cn.mlynn.androidfun.module.home.HomeFragment;
import cn.mlynn.androidfun.module.latestproject.LatestProjectFragment;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.home2
 * @ClassName: HomeFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/29 11:18
 */

@AndroidEntryPoint
public class HomeRootFragment extends ExBaseFragment<LayoutAppbarTabPagerBinding> {

    /**
     * The number of pages (wizard steps) to show in this Fragment.
     */
    private static final int NUM_PAGES = 2;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        initTabsAndPager();
    }

    private void initToolbar() {
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(R.string.home);
        }
    }

    private void initTabsAndPager() {
        //  TabLayout联动ViewPager2
        getBinding().pagers.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if (position == 0)
                    return new HomeFragment();
                else return new LatestProjectFragment();
            }

            @Override
            public int getItemCount() {
                return NUM_PAGES;
            }
        });
        //  Tabs的数据
        String[] titles = {getString(R.string.latest_article),
                getString(R.string.latest_project)};
        new TabLayoutMediator(getBinding().tabs,
                getBinding().pagers,
                (tab, position) -> tab.setText(titles[position])).attach();
    }

    @Override
    protected LayoutAppbarTabPagerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutAppbarTabPagerBinding.inflate(inflater, container, false);
    }
}
