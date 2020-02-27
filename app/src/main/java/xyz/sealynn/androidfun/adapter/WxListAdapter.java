package xyz.sealynn.androidfun.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.model.wxarticle.WxActicle;
import xyz.sealynn.androidfun.module.AboutActivity;
import xyz.sealynn.androidfun.module.web.WebActivity;

/**
 * Created by MichaelLynn on 2020/2/4 16:20
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WxListAdapter extends PagedListAdapter<WxActicle.DatasBean, WxListAdapter.WxArticleViewHolder> {

    public WxListAdapter() {
        super(DIFF_CALLBACK);
    }

    static class WxArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        AppCompatTextView title;
        @BindView(R.id.date)
        AppCompatTextView date;
        @BindView(R.id.like)
        AppCompatImageButton like;

        public WxArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        // each data item is just a string in this case

    }

    @NonNull
    @Override
    public WxArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter,parent,false);
        return new WxArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WxArticleViewHolder holder, int position) {
        if (getItem(position)!=null){
            holder.title.setText(Objects.requireNonNull(getItem(position)).getTitle());
            holder.date.setText(Objects.requireNonNull(getItem(position)).getNiceDate());
            holder.like.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), WebActivity.class);
                intent.putExtra("url", Objects.requireNonNull(getItem(position)).getLink());
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    private static DiffUtil.ItemCallback<WxActicle.DatasBean> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WxActicle.DatasBean>() {
                @Override
                public boolean areItemsTheSame(@NonNull WxActicle.DatasBean oldItem, @NonNull WxActicle.DatasBean newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull WxActicle.DatasBean oldItem, @NonNull WxActicle.DatasBean newItem) {
                    return oldItem.equals(newItem);
                }
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.

            };
}
