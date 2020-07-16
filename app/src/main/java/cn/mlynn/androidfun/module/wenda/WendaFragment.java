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

import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.recycler.adapter.WendaAdapter;

public class WendaFragment extends RefreshRecyclerFragment<WendaViewModel> {

    private WendaAdapter adapter;

    @Override
    protected WendaViewModel initViewModel() {
        WendaViewModel viewModel = new ViewModelProvider(requireActivity()).get(WendaViewModel.class);
        viewModel.initWendaLiveData(getLifecycle());
        return viewModel;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getBinding().getRoot().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        initRecyclerView();
        getViewModel().getWendaLiveData().observe(getViewLifecycleOwner(), articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
        getBinding().refresh.setOnRefreshListener(() -> adapter.refresh());
    }

    private void initRecyclerView() {
        //  问答的Adapter
        adapter = new WendaAdapter();
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
}
