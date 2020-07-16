/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.adapter
 * @ClassName: HomeRecyclerAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/7 10:35
 */
package cn.mlynn.androidfun.recycler.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import cn.mlynn.androidfun.diffutil.ArticleDiffUtilCallBack;
import cn.mlynn.androidfun.databinding.ItemArticleBinding;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.recycler.viewholder.ArticleViewHolder;

public class ArticleAdapter extends ListAdapter<Article, ArticleViewHolder> {

    boolean isTop = false;

    public ArticleAdapter() {
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
        holder.bind(getItem(position), isTop);
    }



    public void setTop(boolean top) {
        isTop = top;
    }

}
