/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.adapter
 * @ClassName: ImageAdapter
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/7 1:59
 */
package cn.mlynn.androidfun.recycler.adapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.module.explore.ExploreRootFragment;
import cn.mlynn.androidfun.utils.ActivityUtils;

public class HomeBannerAdapter extends BannerAdapter<Banner, HomeBannerAdapter.BannerViewHolder> {

    public HomeBannerAdapter(List<Banner> mData) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mData);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        AppCompatImageView imageView = new AppCompatImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, Banner data, int position, int size) {
//        holder.imageView.setImageResource(d);
        Glide
                .with(holder.imageView.getContext())
                .load(data.getImagePath())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            if (TextUtils.equals("https://www.wanandroid.com/navi", data.getUrl())) {
//                ConfirmationAction action =
//                        SpecifyAmountFragmentDirections.confirmationAction()
//                action.setAmount(amount)
                Bundle bundle = new Bundle();
                bundle.putInt(ExploreRootFragment.KEY_TARGET_PAGE, 1);
                Navigation.findNavController(v).navigate(R.id.explore, bundle);
            } else ActivityUtils.startWebActivity(holder.itemView.getContext(), data.getUrl());
        });
//                l -> {
//            if (TextUtils.equals("https://www.wanandroid.com/navi", data.getUrl()))
//                Navigation.
//            else ActivityUtils.startWebActivity(holder.itemView.getContext(), data.getUrl());
//        });
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView imageView;

        public BannerViewHolder(@NonNull AppCompatImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
