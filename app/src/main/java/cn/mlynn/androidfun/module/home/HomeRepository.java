/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.respository
 * @ClassName: BannerRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/8 8:28
 */
package cn.mlynn.androidfun.module.home;

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
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.module.latestproject.LatestProjectPagingSource;
import cn.mlynn.androidfun.net.RequestApi;

public class HomeRepository extends BaseRepository {

    private Pager<Integer, Article> pagerHome;

    @Inject
    public HomeRepository(RequestApi requestApi) {
        super(requestApi);
        pagerHome = new Pager<>(new PagingConfig(20),
                () -> new HomePagingSource(getRequestApi()));
    }

    public void queryBanners(Lifecycle lifecycle, QueryCallBack<Result<List<Banner>>> callBack) {
        query(lifecycle, getRequestApi().getBanner(), callBack);
    }

    public void queryTopArticles(Lifecycle lifecycle,QueryCallBack<Result<List<Article>>> callBack) {
        query(lifecycle, getRequestApi().getTopArticle(), callBack);
    }

    public LiveData<PagingData<Article>> initHomeLiveData(Lifecycle lifecycle) {
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pagerHome)
                , lifecycle);
    }
}
