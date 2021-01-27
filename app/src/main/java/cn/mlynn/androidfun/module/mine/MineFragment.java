package cn.mlynn.androidfun.module.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.FragmentMineBinding;
import cn.mlynn.androidfun.listener.AppBarStateChangeListener;
import cn.mlynn.androidfun.recycler.viewholder.IconTitleViewHolder;
import cn.mlynn.androidfun.recycler.viewholder.UserInformationViewHolder;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MineFragment extends BaseFragment<MineViewModel, FragmentMineBinding> {

    IconTitleViewHolder.onItemClickListener listener = (view, data) -> {
//        if (data.getTitle() == R.string.yearprogress)
//            Navigation.findNavController(view).navigate(R.id.yearProgressActivity);
//        else if (data.getTitle() == R.string.square)
//            Navigation.findNavController(view).navigate(R.id.action_explore_to_squareFragment);
//        else if (data.getTitle() == R.string.wenda)
//            Navigation.findNavController(view).navigate(R.id.action_explore_to_wendaFragment);
    };

    @Override
    protected void initView(Bundle savedInstanceState) {
        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new UserInformationViewHolder(cn.mlynn.androidfun.databinding.ItemIconTwolineBinding
                        .inflate(LayoutInflater.from(getContext()), parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((UserInformationViewHolder)holder).bind(null);
            }

            @Override
            public int getItemCount() {
                return 1;
            }
        });
    }

    private void initToolbar() {
        setHasOptionsMenu(true);    //  加上这句话，menu才会显示出来
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle(R.string.mine);
            }
        }
//        getBinding().appbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state == State.EXPANDED) {
//                    getViewModel().setAppbarStatus(true);
//                    //展开状态
//                } else if (state == State.COLLAPSED) {
//                    getViewModel().setAppbarStatus(false);
//                    //折叠状态
//
//                } else {
//                    //中间状态
//                }
//            }
//        });
    }


    @Override
    protected FragmentMineBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMineBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mine_top_app_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Navigation
                    .findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.settingActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void dismissLoading() {
        if (getBinding() != null && getBinding().refresh.isRefreshing())
            getBinding().refresh.setRefreshing(false);
    }

    @Override
    protected void startLoading() {
        if (getBinding() != null && !getBinding().refresh.isRefreshing())
            getBinding().refresh.setRefreshing(true);
    }

    @Override
    protected MineViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(MineViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getBinding().appbarLayout.post(() -> {
//            getBinding().appbarLayout.setExpanded(getViewModel().getAppbarStatus(), false);
//            initToolbar();
//        });
    }
}
