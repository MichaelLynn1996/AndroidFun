/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.explore
 * @ClassName: ExploreViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:29
 */
package cn.mlynn.androidfun.module.explore;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.Pager;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.util.List;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.HotKey;

public class ExploreViewModel extends BaseViewModel {

    private ExploreRepository repository = new ExploreRepository();

    private LiveData<List<HotKey>> hotKeyLiveData = repository.getHotKeyLiveData();
    private LiveData<List<SearchRecord>> recordLiveData = repository.getRecordLiveData();
    private LiveData<PagingData<Article>> searchResultLiveData;

    private MutableLiveData<String> keywordLiveData = new MutableLiveData<>();

    private boolean isFirstLoaded = false;

    public LiveData<List<HotKey>> getHotKeyLiveData() {
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

    public boolean isFirstLoaded() {
        return isFirstLoaded;
    }

    public void setFirstLoaded(boolean firstLoaded) {
        isFirstLoaded = firstLoaded;
    }

    public void queryHotKey(Lifecycle lifecycle) {
        repository.queryHotKey(lifecycle, getViewControlHelper());
    }

    public void initSearchResultLiveData(Lifecycle lifecycle, Pager<Integer, Article> pager) {
        searchResultLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
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
