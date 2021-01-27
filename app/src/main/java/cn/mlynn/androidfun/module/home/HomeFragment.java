package cn.mlynn.androidfun.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.mlynn.androidfun.databinding.ItemBannerBinding;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.ArticleAdapter;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;
import cn.mlynn.androidfun.recycler.viewholder.BannerViewHolder;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Unit;

@AndroidEntryPoint
public class HomeFragment extends RefreshRecyclerFragment<HomeViewModel> {

    private ArticleAdapter adapterTop;
    private ArticlePagingAdapter adapterList;
    private RecyclerView.Adapter<BannerViewHolder> bannerViewHolderAdapter;

    private ConcatAdapter mergeAdapter;

    private List<Banner> banners = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        //  置顶文章的Adapter
        adapterTop = new ArticleAdapter(true);
        //  首页文章的Adapter
        adapterList = new ArticlePagingAdapter();
        adapterList.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return Unit.INSTANCE;
        });
        // ItemBanner的Adapter
        bannerViewHolderAdapter = new RecyclerView.Adapter<BannerViewHolder>() {

            @NonNull
            @Override
            public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new BannerViewHolder(ItemBannerBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false)
                        , getViewLifecycleOwner());
            }

            @Override
            public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
                holder.bind(banners);
            }

            @Override
            public int getItemCount() {
                return 1;
            }
        };
        mergeAdapter = new ConcatAdapter(bannerViewHolderAdapter, adapterTop, adapterList);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().recyclerView.setAdapter(mergeAdapter);
    }

    @Override
    protected HomeViewModel initViewModel() {
        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.initHomeLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterTop.setStateRestorationPolicy(RecyclerView.Adapter
                .StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        adapterList.setStateRestorationPolicy(RecyclerView.Adapter
                .StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        getViewModel().getHomeLiveData().observe(getViewLifecycleOwner()
                , articles -> adapterList.submitData(getLifecycle(), articles));
        getViewModel().getTopLiveData().observe(getViewLifecycleOwner()
                , homeArticles -> adapterTop.submitList(homeArticles)
        );
        getViewModel().getBanner().observe(getViewLifecycleOwner(), banners
                -> {
            this.banners.clear();
            this.banners.addAll(banners);
            bannerViewHolderAdapter.notifyItemChanged(0);
        });
        getBinding().refresh.setOnRefreshListener(() -> {
            adapterList.refresh();
            getViewModel().initialHomeLoad(getLifecycle());
        });
        if (getViewModel().getBanner().getValue() == null
                || getViewModel().getTopLiveData().getValue() == null)
            getViewModel().initialHomeLoad(getLifecycle());
    }

    @Override
    public void onDestroyView() {
        mergeAdapter = null;
        super.onDestroyView();
    }
}
