
package xyz.sealynn.androidfun.module.collection;

import android.os.Bundle;
import android.view.MenuItem;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.base.ToolbarBaseActivity;

public class CollectionActivity extends ToolbarBaseActivity<CollectionContract.Presenter> implements CollectionContract.View {

    @Override
    protected CollectionContract.Presenter createPresenter() {
        return new CollectionPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_todo;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
