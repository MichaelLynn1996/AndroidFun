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

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;

public class SquareFragment extends RefreshRecyclerFragment<SquareViewModel> {

    private ArticlePagingAdapter articlePagingAdapter;

    @Override
    protected SquareViewModel initViewModel() {
        SquareViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(SquareFragment.class.getSimpleName(), SquareViewModel.class);
        viewModel.initSquareLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        initVisitData();
        initRecyclerView();
        getViewModel().getSquareLiveData().observe(getViewLifecycleOwner()
                , articlePagingData -> articlePagingAdapter.submitData(getLifecycle(), articlePagingData));
        getBinding().refresh.setOnRefreshListener(() -> articlePagingAdapter.refresh());
    }

    private void initRecyclerView() {
        ConcatAdapter concatAdapter = new ConcatAdapter();

//        concatAdapter.addAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//            @NonNull
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        });

        articlePagingAdapter = new ArticlePagingAdapter();
        articlePagingAdapter.addLoadStateListener(loadStates -> {
            if (loadStates.getRefresh() instanceof LoadState.Loading)
                startLoading();
            else if (loadStates.getRefresh() instanceof LoadState.NotLoading)
                dismissLoading();
            return null;
        });
        concatAdapter.addAdapter(articlePagingAdapter);

        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        getBinding().recyclerView.
        getBinding().recyclerView.setAdapter(concatAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
