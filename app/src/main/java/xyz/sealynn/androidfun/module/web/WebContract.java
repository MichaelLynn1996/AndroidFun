package xyz.sealynn.androidfun.module.web;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;

/**
 * Created by SeaLynn0 on 2018/9/5 0:32
 * <p>
 * Email：sealynndev@gmail.com
 */
class WebContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
