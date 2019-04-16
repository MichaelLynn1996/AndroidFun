package xyz.sealynn.androidfun.module.home;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;

/**
 * Created by SeaLynn0 on 2018/12/6 19:46
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface HomeContract {

    interface View extends BaseView {
        void loading(Boolean isLoading);
    }

    interface Presenter extends BasePresenter<View> {
        void getBannerList();
    }
}
