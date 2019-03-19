package xyz.sealynn.androidfun.module.project;

import android.os.Bundle;
import android.view.View;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseLazyLoadFragment;

/**
 * Created by SeaLynn0 on 2018/12/6 20:19
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ProjectFragment extends BaseLazyLoadFragment<ProjectContract.Presenter> implements ProjectContract.View {
    @Override
    protected void initViews(View rootView) {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected ProjectContract.Presenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.frament_project;
    }

    @Override
    protected void initEvent() {

    }
}
