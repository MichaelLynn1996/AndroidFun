package xyz.sealynn.androidfun.module.webcollection;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Objects;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.databinding.FragmentWebCollcetionBinding;
import xyz.sealynn.androidfun.model.WebCollection;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.ToastUtils;
import xyz.sealynn.androidfun.view.SwipeRefreshLayoutAtViewPager2;

/**
 * Created by MichaelLynn on 2019/4/19 16:37
 * <p>
 * Email: sealynndev@gamil.com
 */
public class WebCollectionFragment extends BaseFragment<WebCollectionContract.Presenter, FragmentWebCollcetionBinding> implements WebCollectionContract.View {

//    @BindView(R.id.web_group)
//    ChipGroup group;
//    @BindView(R.id.refresh)
//    SwipeRefreshLayoutAtViewPager2 refreshLayout;

    @Override
    protected FragmentWebCollcetionBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWebCollcetionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected WebCollectionContract.Presenter createPresenter() {
        return new WebCollectionPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {
        getPresenter().getWebs();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {
    }

    @Override
    protected void initEvent() {
        getBinding().refresh.setOnRefreshListener(() -> getPresenter().getWebs());
    }

    @Override
    public void setUpChipGroup(List<WebCollection> collectionList) {
        getBinding().webGroup.removeAllViews();
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
                getBinding().webGroup.addView(chip);
            }
        Logger.d(getBinding().webGroup);
    }

    @Override
    public void setRefreshing(Boolean isRefreshing) {
        getBinding().refresh.setRefreshing(isRefreshing);
    }

}
