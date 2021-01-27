/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.adapter
 * @ClassName: SquareAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 23:15
 */
package cn.mlynn.androidfun.recycler.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;

import cn.mlynn.androidfun.databinding.ItemArticleBinding;
import cn.mlynn.androidfun.recycler.diffutil.ArticleDiffUtilCallBack;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.recycler.viewholder.ArticleViewHolder;

public class ArticlePagingAdapter extends PagingDataAdapter<Article, ArticleViewHolder> {

    public ArticlePagingAdapter() {
        super(new ArticleDiffUtilCallBack());
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(ItemArticleBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
