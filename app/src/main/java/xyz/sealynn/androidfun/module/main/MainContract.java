package xyz.sealynn.androidfun.module.main;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;

/**
 * Created by SeaLynn0 on 2018/9/5 11:40
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface MainContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
        void checkYearProgress();
    }
}
