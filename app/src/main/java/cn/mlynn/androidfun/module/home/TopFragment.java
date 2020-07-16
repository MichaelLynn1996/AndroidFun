package cn.mlynn.androidfun.module.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.ArticleAdapter;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;

public class TopFragment extends RefreshRecyclerFragment<HomeViewModel> {

    private ArticleAdapter adapterTop;
    private ArticlePagingAdapter adapterList;

    private ConcatAdapter mergeAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initRecyclerView();
        getViewModel().getHomeLiveData().observe(getViewLifecycleOwner()
                , articles -> adapterList.submitData(getLifecycle(), articles));
        getViewModel().getTopLiveData().observe(getViewLifecycleOwner()
                , homeArticles -> adapterTop.submitList(homeArticles)
        );
        getBinding().refresh.setOnRefreshListener(() -> {
            adapterList.refresh();
            getViewModel().initialHomeLoad(getLifecycle());
        });
        if (getViewModel().getBanner().getValue() == null
                || getViewModel().getTopLiveData().getValue() == null)
            getViewModel().initialHomeLoad(getLifecycle());
    }

    private void initRecyclerView() {
        //  置顶文章的Adapter
        adapterTop = new ArticleAdapter();
        adapterTop.setTop(true);
        //  首页文章的Adapter
        adapterList = new ArticlePagingAdapter();
        adapterList.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return null;
        });
        mergeAdapter = new ConcatAdapter(adapterTop, adapterList);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().recyclerView.setAdapter(mergeAdapter);
    }

    @Override
    protected HomeViewModel initViewModel() {
        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeFragment.VIEW_MODEL_KEY, HomeViewModel.class);
        viewModel.initHomeLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    public void onDestroyView() {
        mergeAdapter = null;
        super.onDestroyView();
    }
}
