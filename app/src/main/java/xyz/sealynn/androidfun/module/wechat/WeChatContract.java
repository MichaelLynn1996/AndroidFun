package xyz.sealynn.androidfun.module.wechat;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;

/**
 * Created by SeaLynn0 on 2018/12/10 19:38
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface WeChatContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
