/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.wenda
 * @ClassName: WendaFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/9 21:33
 */
package cn.mlynn.androidfun.module.wenda;

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
public class WendaFragment extends BaseFragment<WendaViewModel, LayoutAppbarRefreshRecyclerBinding> {

    private ArticlePagingAdapter adapter;

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
    protected WendaViewModel initViewModel() {
        WendaViewModel viewModel = new ViewModelProvider(requireActivity()).get(WendaViewModel.class);
        viewModel.initWendaLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = getBinding().refreshRecycler;

        getBinding().getRoot().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        initRecyclerView();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getWendaLiveData().observe(getViewLifecycleOwner(), articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
        binding.refresh.setOnRefreshListener(() -> adapter.refresh());

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        NavigationUI.setupWithNavController((MaterialToolbar)getBinding().getRoot().findViewById(R.id.toolbar)
                , navController);
    }

    @Override
    protected LayoutAppbarRefreshRecyclerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutAppbarRefreshRecyclerBinding.inflate(inflater, container, false);
    }

    private void initRecyclerView() {
        //  问答的Adapter
        adapter = new ArticlePagingAdapter();
        adapter.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return Unit.INSTANCE;
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
