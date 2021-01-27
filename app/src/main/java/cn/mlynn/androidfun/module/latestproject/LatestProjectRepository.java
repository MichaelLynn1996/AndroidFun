package cn.mlynn.androidfun.module.latestproject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import javax.inject.Inject;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.net.RequestApi;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.latestproject
 * @ClassName: LatestProjectRepository
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/8/31 11:43
 */
public class LatestProjectRepository extends BaseRepository {

    private Pager<Integer, Article> pagerLatestProject;

    @Inject
    public LatestProjectRepository(RequestApi requestApi) {
        super(requestApi);
        pagerLatestProject = new Pager<>(new PagingConfig(20),
                () -> new LatestProjectPagingSource(getRequestApi()));
    }

    public LiveData<PagingData<Article>> initProjectLiveData(Lifecycle lifecycle) {
        return PagingLiveData.cachedIn(PagingLiveData.getLiveData(pagerLatestProject)
                , lifecycle);
    }
}
