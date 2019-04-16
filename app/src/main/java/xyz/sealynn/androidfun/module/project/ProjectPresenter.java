package xyz.sealynn.androidfun.module.project;

import xyz.sealynn.androidfun.base.BasePresenterImpl;

/**
 * Created by SeaLynn0 on 2018/12/6 20:18
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ProjectPresenter extends BasePresenterImpl<ProjectContract.View> implements ProjectContract.Presenter {

    public ProjectPresenter(ProjectContract.View view) {
        super(view);
    }

    @Override
    public void onResponse(Object t, int what) {

    }
}
