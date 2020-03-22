
package xyz.sealynn.androidfun.module.collection;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayoutMediator;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseActivity;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.databinding.ActivityCollectionBinding;
import xyz.sealynn.androidfun.module.articlecollection.ArticleCollectionFragment;
import xyz.sealynn.androidfun.module.webcollection.WebCollectionFragment;

public class CollectionActivity extends BaseActivity<CollectionContract.Presenter, ActivityCollectionBinding> implements CollectionContract.View {

//    @BindView(R.id.tabs)
//    TabLayout tabs;
//    @BindView(R.id.pagers)
//    ViewPager2 pager;
//    @BindView(R.id.fab)
    FloatingActionButton fab;

    List<BaseFragment> fragments = new ArrayList<>();
    private String[] titles;

    private Animation fab_clock, fab_anticlock;

    BottomSheetDialog dialog;

    @Override
    protected ActivityCollectionBinding initBinding() {
        return ActivityCollectionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected CollectionContract.Presenter createPresenter() {
        return new CollectionPresenter(this);
    }

    @Override
    protected void prepareData() {
        titles = new String[]{
                getString(R.string.article_collection)
                , getString(R.string.common_websites)};

        fragments.add(new ArticleCollectionFragment());
        fragments.add(new WebCollectionFragment());

        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);
    }

    @Override
    protected void initView() {
//        fab.hide();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }

        getBinding().pagers.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });

        new TabLayoutMediator(getBinding().tabs, getBinding().pagers, (tab, position) -> tab.setText(titles[position])).attach();


    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {
//        fab.setOnClickListener(v -> {
//            WebCollectionFragment fragment = (WebCollectionFragment) fragments.get(1);
//            if (fragment.getChipGroupManager().isAllChipCloseIconVisible()) {
//                fragment.getChipGroupManager().disableAllChipCloseIcon();
//            } else {
//                fragment.getChipGroupManager().showAllChipCloseIcon();
//            }
//        });
        dialog = new BottomSheetDialog(this);
        View root = getLayoutInflater().inflate(R.layout.dialog_add_collect, null);
        dialog.setContentView(root);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopup(View view) {
        fab.startAnimation(fab_clock);
        dialog.setOnDismissListener(dialog1 -> {
            fab.startAnimation(fab_anticlock);
        });
        dialog.show();
    }

    public void addOffSideCollection(View view) {
        //TODO:addOffSideCollection
        Logger.d("offSide");
        if (dialog.isShowing())
            dialog.dismiss();
    }

    public void addDomainCollection(View view) {
        //TODO:addDomainCollection
        Logger.d("domain");
        if (dialog.isShowing())
            dialog.dismiss();
    }
}
