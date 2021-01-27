/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.explore
 * @ClassName: ExploreViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:29
 */
package cn.mlynn.androidfun.module.explore;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.paging.PagingData;

import java.util.List;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Friend;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.model.wan.Result;
import io.reactivex.rxjava3.annotations.NonNull;

public class ExploreViewModel extends BaseViewModel {

    private ExploreRepository repository;

    private MutableLiveData<List<HotKey>> hotKeyLiveData;
    private LiveData<List<SearchRecord>> recordLiveData;
    private LiveData<PagingData<Article>> searchResultLiveData;
    private MutableLiveData<String> keywordLiveData;
    private MutableLiveData<List<Friend>> friendLiveData;

    @ViewModelInject
    public ExploreViewModel(ExploreRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
        hotKeyLiveData = new MutableLiveData<>();
        keywordLiveData = new MutableLiveData<>();
        recordLiveData = repository.getRecordLiveData();
        friendLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<HotKey>> getHotKeyLiveData() {
        return hotKeyLiveData;
    }

    public LiveData<List<SearchRecord>> getRecordLiveData() {
        return recordLiveData;
    }

    public LiveData<PagingData<Article>> getSearchResultLiveData() {
        return searchResultLiveData;
    }

    public MutableLiveData<String> getKeywordLiveData() {
        return keywordLiveData;
    }

    public MutableLiveData<List<Friend>> getFriendLiveData() {
        return friendLiveData;
    }

    public void queryHotKey(Lifecycle lifecycle) {
        repository.queryHotKey(lifecycle, new BaseRepository.QueryCallBack<Result<List<HotKey>>>() {
            @Override
            public void onSuccess(Result<List<HotKey>> entity) {
                if (getViewControlHelper().isLoadSuccess(entity.getErrorCode())){
                    hotKeyLiveData.setValue(entity.getData());
                    getViewControlHelper().dismissLoading();
                } else {
                    getViewControlHelper().onLoadFailed(entity.getErrorMsg());
                }
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                getViewControlHelper().onLoadFailed(e);
            }
        });
    }

    public void queryFriend(Lifecycle lifecycle) {
        repository.queryFriend(lifecycle, new BaseRepository.QueryCallBack<Result<List<Friend>>>() {
            @Override
            public void onSuccess(Result<List<Friend>> entity) {
                if (getViewControlHelper().isLoadSuccess(entity.getErrorCode())){
                    friendLiveData.setValue(entity.getData());
                    getViewControlHelper().dismissLoading();
                } else {
                    getViewControlHelper().onLoadFailed(entity.getErrorMsg());
                }
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                getViewControlHelper().onLoadFailed(e);
            }
        });
    }

    public void initSearchResultLiveData(Lifecycle lifecycle, String k) {
//        Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20)
//                , () -> new SearchPagingSource(repository.getRequestApi(), k));
        searchResultLiveData = repository.initSearchResultLiveData(lifecycle, k);
    }

    public void insertSearchRecord(SearchRecord record) {
        repository.insertSearchRecord(record);
    }

    public void removeRecordByText(String text) {
        repository.deleteSearchRecordByText(text);
    }

    public void removeAllRecord() {
        repository.deleteAllRecord();
    }
}
