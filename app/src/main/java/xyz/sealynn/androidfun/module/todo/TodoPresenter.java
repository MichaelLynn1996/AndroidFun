package xyz.sealynn.androidfun.module.todo;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class TodoPresenter extends BasePresenterImpl implements TodoContract.Presenter {

    private final TodoContract.View mView;
    public TodoPresenter(TodoContract.View view) {
        mView = view;
        this.mView.setPresenter(this);
    }
}
