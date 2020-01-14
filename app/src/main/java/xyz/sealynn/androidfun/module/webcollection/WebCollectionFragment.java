package xyz.sealynn.androidfun.module.webcollection;

import android.os.Bundle;
import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

//import com.classic.common.MultipleStatusView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.model.WebCollection;

/**
 * Created by MichaelLynn on 2019/4/19 16:37
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WebCollectionFragment extends BaseFragment<WebCollectionContract.Presenter> implements WebCollectionContract.View {

    @BindView(R.id.web_group)
    ChipGroup group;
    //    @BindView(R.id.fab)
//    FloatingActionButton fab;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    //    @BindView(R.id.status_view)
//    MultipleStatusView statusView;

    private ChipGroupManager chipGroupManager;

    @Override
    protected WebCollectionContract.Presenter createPresenter() {
        return new WebCollectionPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
        chipGroupManager = new ChipGroupManager(group, getContext());
        getPresenter().getWebs();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_web_collcetion;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {
//        statusView.showEmpty();
//        statusView.showContent();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void setUpChipGroup(List<WebCollection> collectionList) {
//        if (collectionList.isEmpty())
////            statusView.showEmpty();
//        else
//            for (WebCollection collection : collectionList) {
//                Chip chip = new Chip(getActivity());
//                chipGroupManager.addChip(chip, collection);
//            }
    }

    public ChipGroupManager getChipGroupManager() {
        return chipGroupManager;
    }
}
