package cn.mlynn.androidfun.module.explore;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
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
import cn.mlynn.androidfun.databinding.LayoutToolbarTabPagerBinding;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.module.navigation.NavigationFragment;
import cn.mlynn.androidfun.module.tree.TreeFragment;
import cn.mlynn.androidfun.module.wenda.WendaFragment;
import cn.mlynn.androidfun.pagingsource.SearchPagingSource;
import cn.mlynn.androidfun.recycler.adapter.ArticlePagingAdapter;
import cn.mlynn.androidfun.recycler.viewholder.TitleChipViewHolder;

public class ExploreRootFragment extends BaseFragment<ExploreViewModel, LayoutToolbarTabPagerBinding> {

    static final String VIEW_MODEL_KEY = ExploreRootFragment.class.getSimpleName();

    public static final String KEY_TARGET_PAGE = "targetPage";

    private TitleChipViewHolder hotKeyRecordViewHolder;
    private TitleChipViewHolder searchRecordViewHolder;

    private List<Chip> recordChip;

    private ArticlePagingAdapter adapter = new ArticlePagingAdapter();
    private RecyclerView.Adapter<RecyclerView.ViewHolder> normalAdapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0) return hotKeyRecordViewHolder;
            else if (viewType == 1) return searchRecordViewHolder;
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getBinding().materialSearchView.hasFocus())
                    getBinding().materialSearchView.clearFocus();
                else
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        initToolbar();
        initSearchRecordViewHolder();
        initSearchView();

        initViewPager2();
        initTabLayout();

        getViewModel()
                .getHotKeyLiveData()
                .observe(getViewLifecycleOwner()
                        , hotKeys -> {
                            List<Chip> chips = new ArrayList<>();
                            for (HotKey k : hotKeys) {
                                Chip chip = new Chip(requireContext());
                                chip.setText(k.getName());
                                chip.setOnClickListener(view ->
                                        getViewModel()
                                                .getKeywordLiveData().setValue(k.getName()));
                                chips.add(chip);
                            }
                            hotKeyRecordViewHolder.bind(chips);
                        });
        getViewModel()
                .getRecordLiveData()
                .observe(getViewLifecycleOwner()
                        , searchRecords -> {
                            recordChip = new ArrayList<>();
                            for (SearchRecord record : searchRecords) {
                                Chip chip = new Chip(requireContext());
                                chip.setText(record.getText());
                                chip.setOnClickListener(view ->
                                        getViewModel()
                                                .getKeywordLiveData().setValue(record.getText()));
                                chip.setOnLongClickListener(view -> {
                                    if (chip.isCloseIconVisible())
                                        for (Chip c : recordChip)
                                            c.setCloseIconVisible(false);
                                    else
                                        for (Chip c : recordChip)
                                            c.setCloseIconVisible(true);
                                    return true;
                                });
                                chip.setOnCloseIconClickListener(view ->
                                        getViewModel().removeRecordByText(chip.getText().toString().trim()));
                                recordChip.add(chip);
                            }
                            searchRecordViewHolder.bind(recordChip);
                        });
        getViewModel()
                .getKeywordLiveData()
                .observe(getViewLifecycleOwner(), s -> {
//                            if (!getBinding().materialSearchView.hasFocus())
//                                getBinding().materialSearchView.requestFocus();
                    getBinding().materialSearchView.setTextQuery(s, true);
                    submitAction(s);
                });

    }

    /**
     * 初始化SearchView
     */
    private void initSearchView() {
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
                submitAction(charSequence.toString().trim());

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
//
        getBinding().materialSearchView.setElevation(0f);
//        setBackgroundStrokeWidth(resources.getDimensionPixelSize(R.dimen.search_stroke_width))
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
                for (Chip chip : recordChip) {
                    if (chip.isCloseIconVisible()) chip.setCloseIconVisible(false);
                }
            }
        });
    }

    private void submitAction(String s) {
        if (!TextUtils.isEmpty(s)) {
            if (!getBinding().materialSearchView.hasFocus())
                getBinding().materialSearchView.requestFocus();
            Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20)
                    , () -> new SearchPagingSource(s.trim()));
            getViewModel().initSearchResultLiveData(getLifecycle(), pager);
            getViewModel().getSearchResultLiveData().observe(getViewLifecycleOwner(),
                    articlePagingData -> adapter.submitData(getLifecycle(), articlePagingData));
            getBinding().materialSearchView.setAdapter(adapter);
            //  添加记录
            SearchRecord record = new SearchRecord();
            record.setText(s.trim());
            getViewModel().insertSearchRecord(record);
            //  关闭键盘
            getBinding().materialSearchView.setFocusable(true);
            getBinding().materialSearchView.setFocusableInTouchMode(true);
            getBinding().materialSearchView.hideKeyboard();

//            InputMethodManager im = (InputMethodManager) requireContext()
//                    .getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(getBinding().materialSearchView.getWindowToken(), 0);
        }
    }

    private void initSearchRecordViewHolder() {
        hotKeyRecordViewHolder = new TitleChipViewHolder(cn.mlynn.androidfun.databinding.ItemTitleChipBinding
                .inflate(LayoutInflater.from(getContext()), getBinding().getRoot(), false)
                , R.string.everybody_searching);
        searchRecordViewHolder = new TitleChipViewHolder(cn.mlynn.androidfun.databinding.ItemTitleChipBinding
                .inflate(LayoutInflater.from(getContext()), getBinding().getRoot(), false)
                , R.string.searching_history);
        searchRecordViewHolder.getBinding().text.setOnClickListener(view -> getViewModel().removeAllRecord());
        searchRecordViewHolder.getBinding().text.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBinding().pagers.post(() -> {
            if (getArguments() != null && getArguments().getInt(KEY_TARGET_PAGE) != 0) {
                getBinding().pagers.setCurrentItem(getArguments().getInt(KEY_TARGET_PAGE));
            }
        });
    }

    private void initTabLayout() {
        //  Tabs的数据
        String[] titles = {getString(R.string.discovery),
                getString(R.string.navigation),
                getString(R.string.knowledge_tree)};
//        int[] icon = {R.drawable.ic_action_home, R.drawable.project, R.drawable.guangchang, R.drawable.wenda};
        new TabLayoutMediator(getBinding().tabs,
                getBinding().pagers,
                (tab, position) -> {
//                    tab.setIcon(icon[position]);
                    tab.setText(titles[position]);
                }).attach();
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
    protected LayoutToolbarTabPagerBinding initBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return LayoutToolbarTabPagerBinding.inflate(getLayoutInflater());
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
        if (getBinding().materialSearchView.hasFocus())
            getBinding().materialSearchView.clearFocus();
        getViewModel().getKeywordLiveData().removeObservers(getViewLifecycleOwner());
        getViewModel().getKeywordLiveData().setValue(null);
        super.onDestroyView();
    }
}
