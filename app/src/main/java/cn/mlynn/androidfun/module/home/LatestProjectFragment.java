package cn.mlynn.androidfun.module.home;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.ProjectAdapter;

public class LatestProjectFragment extends RefreshRecyclerFragment<HomeViewModel> {

    private ProjectAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        getBinding().refresh.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        initRecyclerView();
        getViewModel().getProjectLiveData().observe(getViewLifecycleOwner(), articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
        getBinding().refresh.setOnRefreshListener(() -> adapter.refresh());
//        getViewModel().initialHomeLoad();
        if (getViewModel().getBanner().getValue() == null)
            getViewModel().loadBanners(getLifecycle());
    }

    private void initRecyclerView() {
        adapter = new ProjectAdapter();
        adapter.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return null;
        });
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    protected HomeViewModel initViewModel() {
        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeFragment.VIEW_MODEL_KEY, HomeViewModel.class);
        viewModel.initProjectLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    public void onDestroyView() {
//        binding = null;
        super.onDestroyView();
    }
}
