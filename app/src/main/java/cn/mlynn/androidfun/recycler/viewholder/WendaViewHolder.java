/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: WendaViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/9 22:53
 */
package cn.mlynn.androidfun.recycler.viewholder;

import androidx.annotation.NonNull;

import cn.mlynn.androidfun.databinding.ItemWendaBinding;
import cn.mlynn.androidfun.model.wan.Article;

public class WendaViewHolder extends BaseArticleViewHolder<ItemWendaBinding> {

    @Override
    public void bind(Article data) {
        commonArticleSetting(data, getBinding().rootArticle);
    }

    public WendaViewHolder(@NonNull ItemWendaBinding binding) {
        super(binding);
    }

}
