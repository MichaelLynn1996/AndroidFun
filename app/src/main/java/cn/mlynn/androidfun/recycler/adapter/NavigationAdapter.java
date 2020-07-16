/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.adapter
 * @ClassName: NavigationAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 19:35
 */
package cn.mlynn.androidfun.recycler.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import cn.mlynn.androidfun.databinding.ItemTitleChipBinding;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.NavigationRoot;
import cn.mlynn.androidfun.recycler.viewholder.TitleChipViewHolder;
import cn.mlynn.androidfun.utils.ActivityUtils;

public class NavigationAdapter extends ListAdapter<NavigationRoot, TitleChipViewHolder> {
    public NavigationAdapter() {
        super(new DiffUtil.ItemCallback<NavigationRoot>() {
            @Override
            public boolean areItemsTheSame(@NonNull NavigationRoot oldItem, @NonNull NavigationRoot newItem) {
                return oldItem.getCid() == newItem.getCid();
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull NavigationRoot oldItem, @NonNull NavigationRoot newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public TitleChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TitleChipViewHolder(ItemTitleChipBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleChipViewHolder holder, int position) {
        List<Chip> chips = new ArrayList<>();
        for (Article article : getItem(position).getArticles()) {
            Chip chip = new Chip(holder.itemView.getContext());
            chip.setText(article.getTitle());
            chip.setOnClickListener(l -> ActivityUtils.startWebActivity(holder.itemView.getContext(), article.getLink()));
//            getBinding().chips.addView(chip);
            chips.add(chip);
        }
        if (!TextUtils.isEmpty(getItem(position).getName()))
            holder.bind(chips, getItem(position).getName());
        else holder.bind(chips);
    }
}
