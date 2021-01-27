package cn.mlynn.androidfun.recycler.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseViewHolder;
import cn.mlynn.androidfun.databinding.ItemBannerBinding;
import cn.mlynn.androidfun.model.wan.Banner;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.recycler.viewholder
 * @ClassName: BannerViewHolder
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/8/31 13:39
 */
public class BannerViewHolder extends BaseViewHolder<ItemBannerBinding, List<Banner>> {

    private com.youth.banner.Banner<Banner, BannerImageAdapter<Banner>> banner;

    private LifecycleOwner owner;

    @Override
    public void bind(List<Banner> data) {
        if (!data.isEmpty()) {
            banner = getBinding().getRoot().findViewById(R.id.banner);
            //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————
            if (banner != null && owner != null)
                banner.setAdapter(new BannerImageAdapter<Banner>(data) {

                    @Override
                    public void onBindView(BannerImageHolder holder, Banner data, int position, int size) {
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(data.getImagePath())
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                })
                        .addBannerLifecycleObserver(owner)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(getBinding().getRoot().getContext()));
        }
    }

    public BannerViewHolder(@NonNull ItemBannerBinding binding, LifecycleOwner owner) {
        super(binding);
        this.owner = owner;
    }
}
