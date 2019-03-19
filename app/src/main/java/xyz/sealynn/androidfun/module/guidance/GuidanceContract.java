package xyz.sealynn.androidfun.module.guidance;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;
import xyz.sealynn.androidfun.module.collection.CollectionContract;

/**
 * Created by SeaLynn0 on 2018/12/6 20:08
 * <p>
 * Email：sealynndev@gmail.com
 */
public interface GuidanceContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
