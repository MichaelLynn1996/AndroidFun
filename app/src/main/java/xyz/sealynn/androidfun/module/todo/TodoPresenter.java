package xyz.sealynn.androidfun.module.todo;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/25 17:48
 * <p>
 * Email：sealynndev@gmail.com
 */
public class TodoPresenter extends BasePresenterImpl<TodoContract.View> implements TodoContract.Presenter {

    public TodoPresenter(TodoContract.View view) {
        super(view);
    }

    @Override
    public void onResponse(Object t, int what) {

    }
}
