package xyz.sealynn.androidfun.module.wechat;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/10 19:40
 * <p>
 * Email：sealynndev@gmail.com
 */
public class WeChatPresenter extends BasePresenterImpl<WeChatContract.View> implements WeChatContract.Presenter {

    public WeChatPresenter(WeChatContract.View view) {
        super(view);
    }
}
