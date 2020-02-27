package xyz.sealynn.androidfun.module.webcollection;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.model.WebCollection;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.ToastUtils;
import xyz.sealynn.androidfun.view.SwipeRefreshLayoutAtViewPager2;

/**
 * Created by MichaelLynn on 2019/4/19 16:37
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WebCollectionFragment extends BaseFragment<WebCollectionContract.Presenter> implements WebCollectionContract.View {

    @BindView(R.id.web_group)
    ChipGroup group;
    @BindView(R.id.refresh)
    SwipeRefreshLayoutAtViewPager2 refreshLayout;

    @Override
    protected WebCollectionContract.Presenter createPresenter() {
        return new WebCollectionPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
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
    }

    @Override
    protected void initEvent() {
        refreshLayout.setOnRefreshListener(() -> getPresenter().getWebs());
    }

    @Override
    public void setUpChipGroup(List<WebCollection> collectionList) {
        group.removeAllViews();
        if (collectionList.isEmpty())
            ToastUtils.shortToast(getContext(), "没有收藏");
        else
            for (WebCollection collection : collectionList) {
                Chip chip = new Chip(Objects.requireNonNull(getActivity()));
                chip.setText(collection.getName());
                chip.setOnClickListener(v -> {
                            if (!chip.isCloseIconVisible())
                                ActivityUtils.startWebActivity(getContext(), collection.getLink());
                        }
                );
                chip.setOnLongClickListener(v -> {
                    if (chip.isCloseIconVisible())
                        chip.setCloseIconVisible(false);
                    else
                        chip.setCloseIconVisible(true);
                    return false;
                });
                chip.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
//                        v.layout();
                        return false;
                    }
                });
                chip.setTag(collection);
                group.addView(chip);
            }
        Logger.d(group);
    }

    @Override
    public void setRefreshing(Boolean isRefreshing) {
        refreshLayout.setRefreshing(isRefreshing);
    }

}
