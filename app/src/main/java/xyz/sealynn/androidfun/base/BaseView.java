package xyz.sealynn.androidfun.base;

import android.content.Context;

/**
 * Created by SeaLynn0 on 2018/9/4 23:51
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface BaseView<P> {

    void setPresenter(P presenter);

    Context getContext();
}
