package cn.mlynn.androidfun.module.navigation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.NavigationAdapter;

/**
 * Created by SeaLynn0 on 2018/12/6 20:10
 * <p>
 * Email：sealynndev@gmail.com
 */
public class NavigationFragment extends RefreshRecyclerFragment<NavigationViewModel> {

    private NavigationAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initRecyclerView();
        getViewModel().getNavigationLiveData().observe(this, navigationRoots -> adapter.submitList(navigationRoots));
        getBinding().refresh.setOnRefreshListener(() -> getViewModel().loadNavigation(getLifecycle()));
        if (!getViewModel().isFirstLoaded()) {
            getViewModel().loadNavigation(getLifecycle());
            getViewModel().setFirstLoaded(false);
        }
    }

    private void initRecyclerView() {
        //  问答的Adapter
        adapter = new NavigationAdapter();
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    protected NavigationViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);
    }
}
