/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.recycler
 * @ClassName: ArticleViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/9 21:00
 */
package cn.mlynn.androidfun.recycler.viewholder;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseViewHolder;
import cn.mlynn.androidfun.databinding.ItemArticleBinding;
import cn.mlynn.androidfun.databinding.LayoutArticleBinding;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Tag;
import cn.mlynn.androidfun.utils.ActivityUtils;

public class ArticleViewHolder extends BaseViewHolder<ItemArticleBinding, Article> {
    public ArticleViewHolder(@NonNull ItemArticleBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Article data) {
        if (!TextUtils.isEmpty(data.getEnvelopePic())){
            getBinding().picture.setVisibility(View.VISIBLE);
            Glide
                    .with(getBinding().getRoot())
                    .load(data.getEnvelopePic())
                    .into(getBinding().picture);
        } else {
            getBinding().picture.setVisibility(View.GONE);
        }
        commonArticleSetting(data, getBinding().rootArticle);
    }

    public void bind(Article data, boolean isTop) {
        //  设置置顶
        if (isTop)
            getBinding().rootArticle.pinTop.setVisibility(View.VISIBLE);
        else getBinding().rootArticle.pinTop.setVisibility(View.GONE);
        bind(data);
    }

    private void commonArticleSetting(Article data, LayoutArticleBinding binding) {
        //  设置最新
        if (data.isFresh())
            binding.latest.setVisibility(View.VISIBLE);
        else binding.latest.setVisibility(View.GONE);
        //  设置作者
        if (!TextUtils.isEmpty(data.getAuthor()))
            binding.author.setText(data.getAuthor());
        else if (!TextUtils.isEmpty(data.getShareUser()))
            binding.author.setText(data.getShareUser());
        binding.author.setOnClickListener(v -> {});
        //  设置时间
        if (!TextUtils.isEmpty(data.getNiceDate()))
            binding.time.setText(data.getNiceDate());
        else if (!TextUtils.isEmpty(data.getNiceShareDate()))
            binding.time.setText(data.getNiceShareDate());
        //  设置标题
        if (!TextUtils.isEmpty(data.getTitle()))
            binding.title.setText(Html.fromHtml(data.getTitle()));
        //  设置描述
        if (!TextUtils.isEmpty(data.getDesc())) {
            binding.description.setText(Html.fromHtml(data.getDesc()));
            binding.description.setVisibility(View.VISIBLE);
        } else binding.description.setVisibility(View.GONE);
        //  设置体系
        if (!TextUtils.isEmpty(data.getChapterName())
                && !TextUtils.isEmpty(data.getSuperChapterName()))
            binding.catalog.setText(String.format("%s/%s", data.getSuperChapterName(), data.getChapterName()));
        //  设置Tag
        binding.ownPublish.setVisibility(View.GONE);
        binding.wx.setVisibility(View.GONE);
        binding.wenda.setVisibility(View.GONE);
        if (!data.getTags().isEmpty()) {
            for (Tag tag : data.getTags())
                if (TextUtils.equals(tag.getName(), Tag.OWN_PUBLISH)) {
                    binding.ownPublish.setVisibility(View.VISIBLE);
                } else if (TextUtils.equals(tag.getName(), Tag.WE_CHAT)) {
                    binding.wx.setVisibility(View.VISIBLE);
                } else if (TextUtils.equals(tag.getName(), Tag.WEN_DA)) {
                    binding.wenda.setVisibility(View.VISIBLE);
                    binding.wenda.setOnClickListener(v -> {
                        NavController controller = Navigation.findNavController(v);
                        if (controller.getCurrentDestination() != null
                                && controller.getCurrentDestination().getId() != R.id.wendaFragment)
                            controller.navigate(R.id.wendaFragment);
                    });
                }
        }

        getBinding().getRoot().setOnClickListener(v -> ActivityUtils.startWebActivity(v.getContext(), data.getLink()));
    }
}
