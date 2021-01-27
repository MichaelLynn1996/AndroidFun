/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.square
 * @ClassName: SquareFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 22:08
 */
package cn.mlynn.androidfun.module.square;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.appbar.MaterialToolbar;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.LayoutAppbarRefreshRecyclerBinding;
import cn.mlynn.androidfun.databinding.LayoutRefreshRecyclerBinding;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Unit;

@AndroidEntryPoint
public class SquareFragment extends BaseFragment<SquareViewModel, LayoutAppbarRefreshRecyclerBinding> {

    private ArticlePagingAdapter articlePagingAdapter;

    private LayoutRefreshRecyclerBinding binding;

    @Override
    protected void dismissLoading() {
        if (binding != null && binding.refresh.isRefreshing())
            binding.refresh.setRefreshing(false);
    }

    @Override
    protected void startLoading() {
        if (binding != null && !binding.refresh.isRefreshing())
            binding.refresh.setRefreshing(true);
    }

    @Override
    protected SquareViewModel initViewModel() {
        SquareViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(SquareFragment.class.getSimpleName(), SquareViewModel.class);
        viewModel.initSquareLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = getBinding().refreshRecycler;
//        initVisitData();
        initRecyclerView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getSquareLiveData().observe(getViewLifecycleOwner()
                , articlePagingData -> articlePagingAdapter.submitData(getLifecycle(), articlePagingData));
        binding.refresh.setOnRefreshListener(() -> articlePagingAdapter.refresh());

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        NavigationUI.setupWithNavController((MaterialToolbar)getBinding().getRoot().findViewById(R.id.toolbar)
                , navController);
    }

    private void initRecyclerView() {
        articlePagingAdapter = new ArticlePagingAdapter();
        articlePagingAdapter.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return Unit.INSTANCE;
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        getBinding().recyclerView.
        binding.recyclerView.setAdapter(articlePagingAdapter);
    }

    @Override
    protected LayoutAppbarRefreshRecyclerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutAppbarRefreshRecyclerBinding.inflate(inflater, container, false);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
