package cn.mlynn.androidfun.module.latestproject;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Unit;

@AndroidEntryPoint
public class LatestProjectFragment extends RefreshRecyclerFragment<LatestProjectViewModel> {

    private ArticlePagingAdapter adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        getBinding().refresh.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new ArticlePagingAdapter();
        adapter.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return Unit.INSTANCE;
        });
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getProjectLiveData().observe(getViewLifecycleOwner(), articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
        getBinding().refresh.setOnRefreshListener(() -> adapter.refresh());
    }

    @Override
    protected LatestProjectViewModel initViewModel() {
        LatestProjectViewModel viewModel = new ViewModelProvider(requireActivity()).get(LatestProjectViewModel.class);
        viewModel.initProjectLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    public void onDestroyView() {
//        binding = null;
        super.onDestroyView();
    }
}
