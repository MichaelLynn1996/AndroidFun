/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.recycler
 * @ClassName: ArticleViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/9 21:00
 */
package cn.mlynn.androidfun.recycler.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.databinding.ItemArticleBinding;
import cn.mlynn.androidfun.model.wan.Article;

public class ArticleViewHolder extends BaseArticleViewHolder<ItemArticleBinding> {
    public ArticleViewHolder(@NonNull ItemArticleBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Article data) {
        getBinding()
                .rootArticle
                .getRoot()
                .setBackgroundColor(getBinding()
                        .getRoot()
                        .getContext()
                        .getResources()
                        .getColor(R.color.colorPrimaryDark));
        commonArticleSetting(data, getBinding().rootArticle);
    }

    public void bind(Article data, boolean isTop) {
        //  设置置顶
        if (isTop)
            getBinding().rootArticle.pinTop.setVisibility(View.VISIBLE);
        else getBinding().rootArticle.pinTop.setVisibility(View.GONE);
        bind(data);
    }
}
