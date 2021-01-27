package cn.mlynn.androidfun.module.tree;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import cn.mlynn.androidfun.recycler.adapter.TreeAdapter;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by SeaLynn0 on 2018/12/6 20:05
 * <p>
 * Email：sealynndev@gmail.com
 */
@AndroidEntryPoint
public class TreeFragment extends RefreshRecyclerFragment<TreeViewModel> {

    private TreeAdapter adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getTreeLiveData().observe(getViewLifecycleOwner(), children -> adapter.submitList(children));
        if (getViewModel().getTreeLiveData().getValue() == null)
            getViewModel().loadTree(getLifecycle());
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
