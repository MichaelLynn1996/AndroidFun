
package xyz.sealynn.androidfun.module.collection;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.module.articlecollection.ArticleCollectionFragment;
import xyz.sealynn.androidfun.module.webcollection.WebCollectionFragment;

public class CollectionActivity extends BaseActivity<CollectionContract.Presenter> implements CollectionContract.View {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pagers)
    ViewPager pager;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;

    List<BaseFragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Override
    protected CollectionContract.Presenter createPresenter() {
        return new CollectionPresenter(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void initView() {
//        fab.hide();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }

        fragments.add(new ArticleCollectionFragment());
        fragments.add(new WebCollectionFragment());

        titles.add(getString(R.string.article_collection));
        titles.add(getString(R.string.common_websites));
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        fab.hide();
                        invalidateOptionsMenu();
                        break;
                    case 1:
//                        fab.show();
                        invalidateOptionsMenu();
                        break;
//                    default:
//                        invalidateOptionsMenu();
//                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabs.setupWithViewPager(pager);
//        fab.setOnClickListener(v -> {
//            WebCollectionFragment fragment = (WebCollectionFragment) fragments.get(1);
//            if (fragment.getChipGroupManager().isAllChipCloseIconVisible()) {
//                fragment.getChipGroupManager().disableAllChipCloseIcon();
//            } else {
//                fragment.getChipGroupManager().showAllChipCloseIcon();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_edit:
                WebCollectionFragment fragment = (WebCollectionFragment) fragments.get(1);
                if (fragment.getChipGroupManager().isAllChipCloseIconVisible()) {
                    fragment.getChipGroupManager().disableAllChipCloseIcon();
                } else {
                    fragment.getChipGroupManager().showAllChipCloseIcon();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (pager.getCurrentItem() == 1) {
            getMenuInflater().inflate(R.menu.menu_collection, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
