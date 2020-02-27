package xyz.sealynn.androidfun.module.search;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;

/**
 * Created by SeaLynn0 on 2018/12/26 13:16
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SearchActivity extends BaseActivity<SearchContract.Presenter> implements SearchContract.View {
    @Override
    protected SearchContract.Presenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int bindView() {
        return R.layout.activity_search;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        //设置搜索栏的默认提示
        searchView.setQueryHint("");
        //默认刚进去就打开搜索栏
        searchView.setIconified(false);
        //设置输入文本的EditText
        SearchView.SearchAutoComplete et = searchView.findViewById(R.id.search_src_text);
        //设置搜索栏的默认提示，作用和setQueryHint相同
//        et.setHint("输入商品名或首字母");
        //设置提示文本的颜色
//        et.setHintTextColor(Color.WHITE);
        //设置输入文本的颜色
//        et.setTextColor(Color.WHITE);
        //设置提交按钮是否可见
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, "您输入的文本为" + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
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
