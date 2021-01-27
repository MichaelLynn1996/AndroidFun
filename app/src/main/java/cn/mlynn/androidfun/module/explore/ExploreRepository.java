/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.explore
 * @ClassName: ExploreRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/13 16:42
 */
package cn.mlynn.androidfun.module.explore;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.util.List;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.database.dao.SearchRecordDao;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Friend;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RequestApi;

public class ExploreRepository extends BaseRepository {

//    private HotKeyDao hotKeyDao;
    private SearchRecordDao searchRecordDao;

    @Inject
    public ExploreRepository(RequestApi requestApi, SearchRecordDao searchRecordDao) {
        super(requestApi);
//        this.hotKeyDao = hotKeyDao;
        this.searchRecordDao = searchRecordDao;
    }

//    LiveData<List<HotKey>> getHotKeyLiveData() {
//        return hotKeyDao.loadAllHotKey();
//    }
    LiveData<List<SearchRecord>> getRecordLiveData() {
        return searchRecordDao.loadAllSearchRecord();
    }

    public void queryHotKey(Lifecycle lifecycle, QueryCallBack<Result<List<HotKey>>> callBack) {
        query(lifecycle, getRequestApi().getHotKey(), callBack);
    }

    public void insertSearchRecord(SearchRecord record) {
        getExecutor().execute(() -> {
            searchRecordDao.deleteSearchRecordByContent(record.getText());
            searchRecordDao.insertSearchRecord(record);
        });
    }

    public void deleteSearchRecordByText(String text) {
        getExecutor().execute(() -> searchRecordDao.deleteSearchRecordByContent(text));
    }

    public void deleteAllRecord() {
        getExecutor().execute(() -> searchRecordDao.deleteAllSearchRecord());
    }

    public LiveData<PagingData<Article>> initSearchResultLiveData(Lifecycle lifecycle, String k) {
        Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20)
                , () -> new SearchPagingSource(getRequestApi(), k));
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
    }

    public void queryFriend(Lifecycle lifecycle, QueryCallBack<Result<List<Friend>>> callBack) {
        query(lifecycle, getRequestApi().getFriend(), callBack);
    }
}
