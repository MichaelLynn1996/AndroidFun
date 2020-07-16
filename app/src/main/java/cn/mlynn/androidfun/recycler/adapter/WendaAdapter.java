/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.adapter
 * @ClassName: WendaAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/9 22:52
 */
package cn.mlynn.androidfun.recycler.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;

import cn.mlynn.androidfun.diffutil.ArticleDiffUtilCallBack;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.recycler.viewholder.WendaViewHolder;

public class WendaAdapter extends PagingDataAdapter<Article, WendaViewHolder> {
    public WendaAdapter() {
        super(new ArticleDiffUtilCallBack());
    }

    @NonNull
    @Override
    public WendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WendaViewHolder(cn.mlynn.androidfun.databinding.ItemWendaBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WendaViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
