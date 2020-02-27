package xyz.sealynn.androidfun.module.wxlist;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import xyz.sealynn.androidfun.model.wxarticle.WxActicle;

/**
 * Created by MichaelLynn on 2020/2/4 17:09
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WxPageKeydDataSource extends PageKeyedDataSource<Integer, WxActicle> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, WxActicle> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, WxActicle> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, WxActicle> callback) {

    }
}
