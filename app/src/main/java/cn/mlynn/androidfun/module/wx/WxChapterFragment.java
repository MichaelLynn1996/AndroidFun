package cn.mlynn.androidfun.module.wx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.lapism.search.internal.SearchLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseEventAction;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.LayoutSearchAppbarTabPagerBinding;
import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;

public class WxChapterFragment extends BaseFragment<WxViewModel, LayoutSearchAppbarTabPagerBinding> {

    static final String VIEW_MODEL_KEY = WxChapterFragment.class.getSimpleName();

    private ArticlePagingAdapter adapter = new ArticlePagingAdapter();

    private Chapter currentChapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        getBinding().tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        //  加载数据前不显示TAB
//        getBinding().tabs.setVisibility(View.GONE);
        initSearchView();
    }

    /**
     * 初始化SearchView
     */
    private void initSearchView() {
        getBinding().materialSearchView.setAdapterLayoutManager(new LinearLayoutManager(getContext()));

        getBinding().materialSearchView.setAdapter(adapter);

        getBinding().materialSearchView.setNavigationIconSupport(SearchLayout.NavigationIconSupport.ARROW);
        getBinding().materialSearchView.setOnNavigationClickListener(() ->
                getBinding().materialSearchView.requestFocus());

        getBinding().materialSearchView.setTextHint(R.string.wechat);

        getBinding().materialSearchView.setOnQueryTextListener(new SearchLayout.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(@NotNull CharSequence charSequence) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(@NotNull CharSequence charSequence) {
//                submitAction(charSequence.toString().trim());
                if (!getBinding().materialSearchView.hasFocus())
                    getBinding().materialSearchView.requestFocus();
//                getViewModel().initSearchResultLiveData(getLifecycle(), s.trim());
                getViewModel().getWxSearchResultLiveData(getLifecycle()
                        , currentChapter.getId()
                        , charSequence.toString()).observe(getViewLifecycleOwner(),
                        articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
                getBinding().materialSearchView.hideKeyboard();
                return true;
            }
        });

//
//        setMicIconImageResource(R.drawable.ic_outline_mic_none_24dp)
//        setOnMicClickListener(object : SearchLayout.OnMicClickListener {
//            override fun onMicClick() {
//                if (SearchUtils.isVoiceSearchAvailable(this@MainActivity)) {
//                    SearchUtils.setVoiceSearch(
//                            this@MainActivity,
//                    getString(R.string.speak)
//                        )
//                }
//            }
//        })
//
//        setMenuIconImageResource(R.drawable.layer_list_settings)
//        setOnMenuClickListener(object : SearchLayout.OnMenuClickListener {
//            override fun onMenuClick() {
//
//            }
//        })
        getBinding().materialSearchView.setOnNavigationClickListener(new SearchLayout.OnNavigationClickListener() {
            @Override
            public void onNavigationClick() {
                if (!getBinding().materialSearchView.hasFocus())
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
            }
        });
//
        getBinding().materialSearchView.setElevation(0f);
//        setBackgroundStrokeWidth(resources.getDimensionPixelSize(R.dimen.search_stroke_width))
//        setBackgroundStrokeColor(
//                ContextCompat.getColor(
//                        this@MainActivity,
//        R.color.divider
//                )
//            )

//        getBinding().materialSearchView.setOnFocusChangeListener(hasFocus -> {
//            if (hasFocus) {
//                getBinding().materialSearchView
//                        .setNavigationIconSupport(SearchLayout.NavigationIconSupport.ARROW);
//            } else {
//                getBinding().materialSearchView
//                        .setNavigationIconSupport(SearchLayout.NavigationIconSupport.SEARCH);
//                for (Chip chip : recordChip) {
//                    if (chip.isCloseIconVisible()) chip.setCloseIconVisible(false);
//                }
//            }
//        });
    }

    @Override
    protected LayoutSearchAppbarTabPagerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutSearchAppbarTabPagerBinding.inflate(inflater, container, false);
    }

    @Override
    protected void dismissLoading() {

    }

    @Override
    protected void startLoading() {

    }



    @Override
    protected WxViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(VIEW_MODEL_KEY, WxViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //  观察数据
        getViewModel().getChapterLiveData().observe(getViewLifecycleOwner(), chapters -> {
//            getBinding().tabs.setVisibility(View.VISIBLE);
            initTagsAndPager(chapters);
        });
        getViewModel().queryWxChapters(getLifecycle());

    }


//    protected void setBaseEventAction(BaseEventAction baseEventAction) {
//        if (baseEventAction != null) {
//            if (baseEventAction.isCurrentAction(BaseEventAction.ACTION.SHOW_LOADING))
//                startLoading();
//            else if (baseEventAction.isCurrentAction(BaseEventAction.ACTION.DISMISS_LOADING))
//                dismissLoading();
//            else if (baseEventAction.isCurrentAction(BaseEventAction.ACTION.SHOW_TOAST))
//                showToast(baseEventAction.getMessage());
//        }
//    }

    private void initTagsAndPager(List<Chapter> chapters) {
        //  TabLayout联动ViewPager2
        getBinding().pagers.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment = new WxArticleFragment();
                Bundle args = new Bundle();
                args.putString("chapter", new Gson().toJson(chapters.get(position)));
                fragment.setArguments(args);
                return fragment;
            }

            @Override
            public int getItemCount() {
                return chapters.size();
            }
        });
        new TabLayoutMediator(getBinding().tabs,
                getBinding().pagers,
                (tab, position) -> tab.setText(chapters.get(position).getName())).attach();
        getBinding().pagers.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentChapter = chapters.get(position);
                getBinding().materialSearchView.setTextHint(currentChapter.getName());
            }
        });
    }

}