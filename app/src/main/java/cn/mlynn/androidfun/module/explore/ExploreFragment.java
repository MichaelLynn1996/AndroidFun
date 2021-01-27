/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module
 * @ClassName: ExploreFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/12 0:10
 */
package cn.mlynn.androidfun.module.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.databinding.ItemIconTitleBinding;
import cn.mlynn.androidfun.model.wan.Friend;
import cn.mlynn.androidfun.module.RefreshRecyclerFragment;
import cn.mlynn.androidfun.module.wx.WxChapterFragmentArgs;
import cn.mlynn.androidfun.recycler.viewholder.IconTitleViewHolder;
import cn.mlynn.androidfun.recycler.viewholder.TitleChipViewHolder;
import cn.mlynn.androidfun.utils.ActivityUtils;

public class ExploreFragment extends RefreshRecyclerFragment<ExploreViewModel> {

    IconTitleViewHolder.onItemClickListener listener = (view, data) -> {
        if (data.getTitle() == R.string.yearprogress)
            Navigation.findNavController(view).navigate(R.id.yearProgressActivity);
        else if (data.getTitle() == R.string.square)
            Navigation.findNavController(view).navigate(R.id.action_explore_to_squareFragment);
        else if (data.getTitle() == R.string.wenda)
            Navigation.findNavController(view).navigate(R.id.action_explore_to_wendaFragment);
        else if (data.getTitle() == R.string.wechat)
            Navigation.findNavController(view).navigate(R.id.wxChapterFragment);
    };

    private TitleChipViewHolder friendViewHolder;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initFriendViewHolder();
        initRecyclerView();
    }

    private void initFriendViewHolder() {
        friendViewHolder = new TitleChipViewHolder(cn.mlynn.androidfun.databinding.ItemTitleChipBinding
                .inflate(LayoutInflater.from(getContext()), getBinding().getRoot(), false)
                , R.string.common_websites);
        friendViewHolder.itemView.setVisibility(View.GONE);
    }


    private void initRecyclerData(List<IconTitleViewHolder.Data> list) {

//        SharedPreferences sp = null;
//        if (getContext() != null){
//            sp = PreferenceManager.getDefaultSharedPreferences(getContext());
//        }

//        if (null != sp){
//            if (sp.getBoolean("showSquare", true)){
        list.add(new IconTitleViewHolder.Data(R.drawable.guangchang, R.string.square));
//            }
//            if (sp.getBoolean("showNavigation", true)){
//                list.add(new IconTitleViewHolder.Data(R.drawable.daohang, R.string.guidance));
//            }
//            if (sp.getBoolean("showQAndA", true)){
        list.add(new IconTitleViewHolder.Data(R.drawable.wenda, R.string.wenda));
//            }
//            if (sp.getBoolean("showKnowledgeTree", true)){
//                list.add(new IconTitleViewHolder.Data(R.drawable.zhishi, R.string.knowledge_tree));
//            }
//            if (sp.getBoolean("showProject", true)){
        list.add(new IconTitleViewHolder.Data(R.drawable.project, R.string.project));
//            }
//            if (sp.getBoolean("showWeChatOfficialAccount", true)){
        list.add(new IconTitleViewHolder.Data(R.drawable.weixin, R.string.wechat));
//            }
//            if (sp.getBoolean("showYearProgress", true)){
        list.add(new IconTitleViewHolder.Data(R.drawable.progress, R.string.yearprogress));
        list.add(new IconTitleViewHolder.Data(R.drawable.ic_action_google, R.string.search_repository));
//            }

    }

    private void initRecyclerView() {
        List<IconTitleViewHolder.Data> list = new ArrayList<>();
        initRecyclerData(list);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == list.size())
                    return friendViewHolder;
                else
                    return new IconTitleViewHolder(ItemIconTitleBinding.inflate(LayoutInflater
                            .from(parent.getContext()), parent, false), listener);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                if (position != list.size() && holder instanceof IconTitleViewHolder)
                    ((IconTitleViewHolder) holder).bind(list.get(position));
            }

            @Override
            public int getItemCount() {
                return list.size() + 1;
            }

            @Override
            public int getItemViewType(int position) {
                return position;
            }
        });
    }

    @Override
    protected ExploreViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(ExploreRootFragment.VIEW_MODEL_KEY, ExploreViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getFriendLiveData().observe(getViewLifecycleOwner(), friends -> {
            if (friendViewHolder.itemView.getVisibility() != View.VISIBLE)
                friendViewHolder.itemView.setVisibility(View.VISIBLE);
            List<Chip> chips = new ArrayList<>();
            for (Friend friend : friends) {
                Chip chip = new Chip(requireContext());
                chip.setText(friend.getName());
                chip.setOnClickListener(view1 -> {
                    ActivityUtils.startWebActivity(requireContext(), friend.getLink());
                });
                chips.add(chip);
            }
            friendViewHolder.bind(chips);
        });
        if (getViewModel().getFriendLiveData().getValue() == null)
            getViewModel().queryFriend(getLifecycle());
        getBinding().refresh.setOnRefreshListener(() -> {
            getViewModel().queryHotKey(getLifecycle());
            getViewModel().queryFriend(getLifecycle());
        });
    }
}
