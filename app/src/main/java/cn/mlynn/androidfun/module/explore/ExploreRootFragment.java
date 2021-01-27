package cn.mlynn.androidfun.module.explore;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lapism.search.internal.SearchLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.LayoutSearchAppbarTabPagerBinding;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.module.navigation.NavigationFragment;
import cn.mlynn.androidfun.module.tree.TreeFragment;
import cn.mlynn.androidfun.module.wenda.WendaFragment;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;
import cn.mlynn.androidfun.recycler.viewholder.TitleChipViewHolder;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ExploreRootFragment extends BaseFragment<ExploreViewModel, LayoutSearchAppbarTabPagerBinding> {

    static final String VIEW_MODEL_KEY = ExploreRootFragment.class.getSimpleName();

    private List<SearchRecord> records = new ArrayList<>();
    private List<HotKey> hotKeys = new ArrayList<>();

    private boolean isCloseIconVisible = false;

    private ArticlePagingAdapter adapter = new ArticlePagingAdapter();
    private RecyclerView.Adapter<TitleChipViewHolder> normalAdapter = new RecyclerView.Adapter<TitleChipViewHolder>() {
        @NonNull
        @Override
        public TitleChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            if (viewType == 0) {
                TitleChipViewHolder hotKeyRecordViewHolder = new TitleChipViewHolder(cn.mlynn.androidfun.databinding.ItemTitleChipBinding
                        .inflate(LayoutInflater.from(getContext()), getBinding().getRoot(), false)
                        , R.string.everybody_searching);
                return hotKeyRecordViewHolder;
            } else {
                TitleChipViewHolder searchRecordViewHolder = new TitleChipViewHolder(cn.mlynn.androidfun.databinding.ItemTitleChipBinding
                        .inflate(LayoutInflater.from(getContext()), getBinding().getRoot(), false)
                        , R.string.searching_history);
                searchRecordViewHolder.getBinding().text.setOnClickListener(view -> getViewModel().removeAllRecord());
                searchRecordViewHolder.getBinding().text.setVisibility(View.VISIBLE);
                return searchRecordViewHolder;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull TitleChipViewHolder holder, int position) {
            List<Chip> chips = new ArrayList<>();
            if (position == 0) {
                for (HotKey k : hotKeys) {
                    Chip chip = new Chip(requireContext());
                    chip.setText(k.getName());
                    chip.setOnClickListener(view ->
                            getViewModel()
                                    .getKeywordLiveData().setValue(k.getName()));
                    chips.add(chip);
                }
            } else {
                for (SearchRecord record : records) {
                    Chip chip = new Chip(requireContext());
                    chip.setText(record.getText());
                    chip.setOnClickListener(view ->
                            getViewModel()
                                    .getKeywordLiveData().setValue(record.getText()));
                    chip.setOnLongClickListener(view -> {
                        if (chip.isCloseIconVisible()) {
                            for (Chip c : chips)
                                c.setCloseIconVisible(false);
                            isCloseIconVisible = false;
                        } else {
                            for (Chip c : chips)
                                c.setCloseIconVisible(true);
                            isCloseIconVisible = true;
                        }
                        return true;
                    });
                    chip.setCloseIconVisible(isCloseIconVisible);
                    chip.setOnCloseIconClickListener(view ->
                            getViewModel().removeRecordByText(chip.getText().toString().trim()));
                    chips.add(chip);
                }
            }
            holder.bind(chips);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    };

    @Override
    protected void initView(Bundle savedInstanceState) {
//        initToolbar();
        initSearchView();

        initViewPager2();
        initTabLayout();
    }

    /**
     * 初始化SearchView
     */
    private void initSearchView() {
        getBinding().materialSearchView.setClearFocusOnBackPressed(true);
        getBinding().materialSearchView.setAdapterLayoutManager(new LinearLayoutManager(getContext()));

        getBinding().materialSearchView.setAdapter(normalAdapter);


        getBinding().materialSearchView.setNavigationIconSupport(SearchLayout.NavigationIconSupport.SEARCH);
        getBinding().materialSearchView.setOnNavigationClickListener(() ->
                getBinding().materialSearchView.requestFocus());

        getBinding().materialSearchView.setTextHint(R.string.discovery);

        getBinding().materialSearchView.setOnQueryTextListener(new SearchLayout.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(@NotNull CharSequence charSequence) {
                if (TextUtils.isEmpty(charSequence))
                    getBinding().materialSearchView.setAdapter(normalAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(@NotNull CharSequence charSequence) {
//                submitAction(charSequence.toString().trim());
                if (!TextUtils.isEmpty(charSequence)) {
                    if (!getBinding().materialSearchView.hasFocus())
                        getBinding().materialSearchView.requestFocus();
                    getViewModel().initSearchResultLiveData(getLifecycle(), charSequence.toString().trim());
                    getViewModel().getSearchResultLiveData().observe(getViewLifecycleOwner(),
                            articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
                    getBinding().materialSearchView.setAdapter(adapter);
                    //  添加记录
                    SearchRecord record = new SearchRecord();
                    record.setText(charSequence.toString().trim());
                    getViewModel().insertSearchRecord(record);
                    //  关闭键盘
                    getBinding().materialSearchView.setFocusable(true);
                    getBinding().materialSearchView.setFocusableInTouchMode(true);
                    getBinding().materialSearchView.hideKeyboard();
                }
//                getBinding().materialSearchView.hideKeyboard();
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
//
        getBinding().materialSearchView.setElevation(0f);
//        getBinding().materialSearchView.setBackgroundStrokeWidth(0);
//        setBackgroundStrokeColor(
//                ContextCompat.getColor(
//                        this@MainActivity,
//        R.color.divider
//                )
//            )

        getBinding().materialSearchView.setOnFocusChangeListener(hasFocus -> {
            if (hasFocus) {
                getBinding().materialSearchView
                        .setNavigationIconSupport(SearchLayout.NavigationIconSupport.ARROW);
            } else {
                getBinding().materialSearchView
                        .setNavigationIconSupport(SearchLayout.NavigationIconSupport.SEARCH);
                isCloseIconVisible = false;
                normalAdapter.notifyItemChanged(1);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getViewModel()
                .getHotKeyLiveData()
                .observe(getViewLifecycleOwner()
                        , hotKeys -> {
                            this.hotKeys.clear();
                            this.hotKeys.addAll(hotKeys);
                            normalAdapter.notifyItemChanged(0);
                        });
        getViewModel()
                .getRecordLiveData()
                .observe(getViewLifecycleOwner()
                        , searchRecords -> {
                            this.records.clear();
                            this.records.addAll(searchRecords);
                            normalAdapter.notifyItemChanged(1);
                        });
        getViewModel()
                .getKeywordLiveData()
                .observe(getViewLifecycleOwner(), s -> getBinding().materialSearchView.setTextQuery(s, true));
        if (getViewModel().getHotKeyLiveData().getValue() == null)
            getViewModel().queryHotKey(getLifecycle());
    }

    private void initTabLayout() {
        //  Tabs的数据
        String[] titles = {getString(R.string.discovery),
                getString(R.string.navigation),
                getString(R.string.knowledge_tree)};
        new TabLayoutMediator(getBinding().tabs,
                getBinding().pagers,
                (tab, position) -> tab.setText(titles[position])).attach();
    }

    private void initViewPager2() {
        //  TabLayout联动ViewPager2
        getBinding().pagers.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if (position == 0)
                    return new ExploreFragment();
                else if (position == 1)
                    return new NavigationFragment();
                else if (position == 2)
                    return new TreeFragment();
                else if (position == 3)
                    return new WendaFragment();
                return new ExploreFragment();
            }

            @Override
            public int getItemCount() {
                return 3;
            }


        });
    }

    @Override
    protected LayoutSearchAppbarTabPagerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutSearchAppbarTabPagerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void dismissLoading() {

    }

    @Override
    protected void startLoading() {

    }

    @Override
    protected ExploreViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(ExploreRootFragment.VIEW_MODEL_KEY, ExploreViewModel.class);
    }

    @Override
    public void onDestroyView() {
        getViewModel().getKeywordLiveData().removeObservers(getViewLifecycleOwner());
        getViewModel().getKeywordLiveData().setValue(null);
        super.onDestroyView();
    }
}
