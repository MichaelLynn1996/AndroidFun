/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: BaseArticleViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/9 23:11
 */
package cn.mlynn.androidfun.recycler.viewholder;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.orhanobut.logger.Logger;

import cn.mlynn.androidfun.base.BaseViewHolder;
import cn.mlynn.androidfun.databinding.LayoutArticleBinding;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.utils.ActivityUtils;

public abstract class BaseArticleViewHolder<VB extends ViewBinding> extends BaseViewHolder<VB, Article> {


    public BaseArticleViewHolder(@NonNull VB binding) {
        super(binding);
    }

    void commonArticleSetting(Article data, LayoutArticleBinding binding) {
        //  设置最新
        if (data.isFresh())
            binding.latest.setVisibility(View.VISIBLE);
        else binding.latest.setVisibility(View.GONE);
        //  设置作者
        if (!TextUtils.isEmpty(data.getAuthor()))
            binding.author.setText(data.getAuthor());
        else if (!TextUtils.isEmpty(data.getShareUser()))
            binding.author.setText(data.getShareUser());
        //  设置时间
        if (!TextUtils.isEmpty(data.getNiceDate()))
            binding.time.setText(data.getNiceDate());
        else if (!TextUtils.isEmpty(data.getNiceShareDate()))
            binding.time.setText(data.getNiceShareDate());
        //  设置标题
        if (!TextUtils.isEmpty(data.getTitle()))
            binding.title.setText(Html.fromHtml(data.getTitle()));
        //  设置描述
        if (!TextUtils.isEmpty(data.getDesc())){
            binding.description.setText(Html.fromHtml(data.getDesc()));
            binding.description.setVisibility(View.VISIBLE);
        }else binding.description.setVisibility(View.GONE);
        //  设置体系
        if (!TextUtils.isEmpty(data.getChapterName())
                && !TextUtils.isEmpty(data.getSuperChapterName()))
            binding.catalog.setText(String.format("%s/%s", data.getSuperChapterName(), data.getChapterName()));

        binding.author.setOnClickListener(v -> Logger.d("作者"));
        getBinding().getRoot().setOnClickListener(v -> ActivityUtils.startWebActivity(v.getContext(), data.getLink()));
    }
}
