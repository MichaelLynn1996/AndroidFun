package cn.mlynn.androidfun.module.wx;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.util.List;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RequestApi;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.wx
 * @ClassName: WxRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/27 20:31
 */
public class WxRepository extends BaseRepository {

    @Inject
    public WxRepository(RequestApi requestApi) {
        super(requestApi);
    }

    public void queryWxChapter(Lifecycle lifecycle, QueryCallBack<Result<List<Chapter>>> queryCallBack) {
        query(lifecycle, getRequestApi().getWXChapter(), queryCallBack);
    }

    public LiveData<PagingData<Article>> initWxArticleLiveData(Lifecycle lifecycle, int id) {
        Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20)
                , () -> new WxPagingSource(getRequestApi(), id));
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
    }

    public LiveData<PagingData<Article>> initWxSearchResultLiveData(Lifecycle lifecycle, int id, String k) {
        Pager<Integer, Article> pager = new Pager<>(new PagingConfig(20)
                , () -> new WxSearchPagingSource(getRequestApi(), k, id));
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager)
                , lifecycle);
    }
}
