package cn.mlynn.androidfun.module.wx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.LayoutRefreshRecyclerBinding;
import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Unit;

import static cn.mlynn.androidfun.module.wx.WxChapterFragment.VIEW_MODEL_KEY;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.wx
 * @ClassName: WxArticleFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/27 20:41
 */
@AndroidEntryPoint
public class WxArticleFragment extends RefreshRecyclerFragment<WxViewModel> {

    private ArticlePagingAdapter articlePagingAdapter;

    private Chapter chapter;

    @Override
    protected WxViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(VIEW_MODEL_KEY, WxViewModel.class);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
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

        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        getBinding().recyclerView.
        getBinding().recyclerView.setAdapter(articlePagingAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
//        bundle.getInt()
        if (bundle != null)
            chapter = new Gson().fromJson(bundle.getString("chapter"), Chapter.class);
        if (chapter != null) {
            getViewModel().getWxArticleLiveData(getLifecycle(), chapter.getId()).observe(getViewLifecycleOwner()
                    , articlePagingData -> articlePagingAdapter.submitData(getLifecycle(), articlePagingData));
            getBinding().refresh.setOnRefreshListener(() -> articlePagingAdapter.refresh());
        }
    }

    @Override
    protected LayoutRefreshRecyclerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutRefreshRecyclerBinding.inflate(inflater, container, false);
    }
}
