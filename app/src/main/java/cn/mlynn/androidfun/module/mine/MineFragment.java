package cn.mlynn.androidfun.module.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.FragmentMineBinding;
import cn.mlynn.androidfun.recycler.viewholder.IconTitleViewHolder;
import cn.mlynn.androidfun.recycler.viewholder.UserInformationViewHolder;

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
    protected void init(Bundle savedInstanceState) {
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
}
