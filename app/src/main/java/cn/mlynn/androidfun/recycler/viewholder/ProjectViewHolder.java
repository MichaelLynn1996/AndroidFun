/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: ProjectViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/6 0:44
 */
package cn.mlynn.androidfun.recycler.viewholder;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import cn.mlynn.androidfun.databinding.ItemLatestProjectBinding;
import cn.mlynn.androidfun.model.wan.Article;

public class ProjectViewHolder extends BaseArticleViewHolder<ItemLatestProjectBinding> {
    public ProjectViewHolder(@NonNull ItemLatestProjectBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Article data) {
        if (!TextUtils.isEmpty(data.getEnvelopePic()))
            Glide
                    .with(getBinding().getRoot())
                    .load(data.getEnvelopePic())
                    .into(getBinding().picture);
        commonArticleSetting(data, getBinding().rootArticle);
    }
}
