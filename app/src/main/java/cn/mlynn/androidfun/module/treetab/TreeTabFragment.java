package cn.mlynn.androidfun.module.treetab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;

import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.LayoutAppbarTabPagerBinding;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.treetab
 * @ClassName: TreeTabFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/30 21:23
 */
public class TreeTabFragment extends BaseFragment<TreeTabViewModel, LayoutAppbarTabPagerBinding> {

    static final String VIEW_MODEL_KEY = TreeTabFragment.class.getSimpleName();

    @Override
    protected void dismissLoading() {

    }

    @Override
    protected void startLoading() {

    }

    @Override
    protected TreeTabViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(VIEW_MODEL_KEY, TreeTabViewModel.class);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getBinding().tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        //  加载数据前不显示TAB
        getBinding().tabs.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected LayoutAppbarTabPagerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutAppbarTabPagerBinding.inflate(inflater, container, false);
    }
}
