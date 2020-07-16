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

import java.util.List;

import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.dao.HotKeyDao;
import cn.mlynn.androidfun.dao.SearchRecordDao;
import cn.mlynn.androidfun.model.local.SearchRecord;
import cn.mlynn.androidfun.model.wan.HotKey;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RetrofitManager;
import io.reactivex.rxjava3.annotations.NonNull;

public class ExploreRepository extends BaseRepository {

    HotKeyDao hotKeyDao = APP.getDataBase().getHotKeyDao();
    SearchRecordDao searchRecordDao = APP.getDataBase().getSearchRecordDao();

    LiveData<List<HotKey>> getHotKeyLiveData() {
        return hotKeyDao.loadAllHotKey();
    }
    LiveData<List<SearchRecord>> getRecordLiveData() {
        return searchRecordDao.loadAllSearchRecord();
    }

    public void queryHotKey(Lifecycle lifecycle, ExploreViewModel.ViewControlHelper helper) {
        query(lifecycle, RetrofitManager.getInstance().createReq().getHotKey(), new QueryCallBack<Result<List<HotKey>>>() {
            @Override
            public void onSuccess(Result<List<HotKey>> entity) {
                if (helper.isLoadSuccess(entity.getErrorCode())){
                    getExecutor().execute(() -> {
                        hotKeyDao.deleteAllHotKey();
                        hotKeyDao.insertHotKey(entity.getData());
                    });
                    helper.dismissLoading();
                } else {
                    helper.onLoadFailed(entity.getErrorMsg());
                }
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                helper.onLoadFailed(e);
            }
        });
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
}
