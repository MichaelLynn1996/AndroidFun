package cn.mlynn.androidfun.module.tree;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.recycler.adapter.TreeAdapter;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 20:05
 * <p>
 * Email：sealynndev@gmail.com
 */
public class TreeFragment extends RefreshRecyclerFragment<TreeViewModel> {

    private TreeAdapter adapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        initRecyclerView();
        initRefresh();
        getViewModel().getTreeLiveData().observe(this, children -> adapter.submitList(children));
        if (!getViewModel().isFirstLoaded()) {
            getViewModel().loadTree(getLifecycle());
            getViewModel().setFirstLoaded(false);
        }
    }

    private void initRefresh() {
        getBinding().refresh.setOnRefreshListener(() -> getViewModel().loadTree(getLifecycle()));
    }

    private void initRecyclerView() {
        //  问答的Adapter
        adapter = new TreeAdapter();
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    protected TreeViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(TreeViewModel.class);
    }
}
