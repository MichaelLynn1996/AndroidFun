package xyz.sealynn.androidfun.module.todo;

import android.os.Bundle;
import android.view.MenuItem;

import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.databinding.ActivityTodoBinding;

public class TodoActivity extends BaseActivity<TodoContract.Presenter, ActivityTodoBinding> implements TodoContract.View {

    @Override
    protected ActivityTodoBinding initBinding() {
        return ActivityTodoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected TodoContract.Presenter createPresenter() {
        return new TodoPresenter(this);
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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
