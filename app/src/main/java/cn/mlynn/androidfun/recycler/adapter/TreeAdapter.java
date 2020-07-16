/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.adapter
 * @ClassName: TreeAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 20:59
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
import cn.mlynn.androidfun.model.wan.Children;
import cn.mlynn.androidfun.recycler.viewholder.TitleChipViewHolder;
import cn.mlynn.androidfun.utils.ActivityUtils;

public class TreeAdapter extends ListAdapter<Children, TitleChipViewHolder> {
    public TreeAdapter() {
        super(new DiffUtil.ItemCallback<Children>() {
            @Override
            public boolean areItemsTheSame(@NonNull Children oldItem, @NonNull Children newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull Children oldItem, @NonNull Children newItem) {
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
//        Children children = (Children)data;
        List<Chip> chips = new ArrayList<>();
        if (getItem(position).getChildren() != null)
            for (Children c : getItem(position).getChildren()) {
                Chip chip = new Chip(holder.itemView.getContext());
                chip.setText(c.getName());
//                chip.setOnClickListener(l -> ActivityUtils.startWebActivity(holder.itemView.getContext(), c.getLink()));
                chips.add(chip);
            }
        if (!TextUtils.isEmpty(getItem(position).getName()))
            holder.bind(chips, getItem(position).getName());
        else holder.bind(chips);
    }
}
