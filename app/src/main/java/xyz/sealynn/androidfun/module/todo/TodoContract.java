package xyz.sealynn.androidfun.module.todo;

import xyz.sealynn.androidfun.base.BasePresenter;
import xyz.sealynn.androidfun.base.BaseView;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class TodoContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
