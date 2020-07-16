/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.module.home2
 * @ClassName: HomeFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/7 0:45
 */
package cn.mlynn.androidfun.module.home;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import java.util.List;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.pagingsource.HomePagingSource;
import cn.mlynn.androidfun.pagingsource.LatestProjectPagingSource;
import io.reactivex.rxjava3.annotations.NonNull;

public class HomeViewModel extends BaseViewModel {

    private HomeRepository repository = new HomeRepository();

    private MutableLiveData<List<Banner>> banner;

    private MutableLiveData<List<Article>> topLiveData;
    private LiveData<PagingData<Article>> homeLiveData;
    private LiveData<PagingData<Article>> projectLiveData;

    private Pager<Integer, Article> pagerHome = new Pager<>(new PagingConfig(20), HomePagingSource::new);
    private Pager<Integer, Article> pagerLatestProject = new Pager<>(new PagingConfig(20), LatestProjectPagingSource::new);

    public HomeViewModel() {
        super();
        topLiveData = new MutableLiveData<>();
        banner = new MutableLiveData<>();
    }

    public MutableLiveData<List<Banner>> getBanner() {
        return banner;
    }

    public MutableLiveData<List<Article>> getTopLiveData() {
        return topLiveData;
    }

    public LiveData<PagingData<Article>> getHomeLiveData() {
        return homeLiveData;
    }

    public LiveData<PagingData<Article>> getProjectLiveData() {
        return projectLiveData;
    }

    public void initialHomeLoad(Lifecycle lifecycle) {
        loadBanners(lifecycle);
        repository.queryTopArticles(lifecycle, new BaseRepository.QueryCallBack<Result<List<Article>>>() {
            @Override
            public void onSuccess(Result<List<Article>> entity) {
                if (getViewControlHelper().isLoadSuccess(entity.getErrorCode())) {
                    topLiveData.setValue(entity.getData());
                } else {
                    showToast(entity.getErrorMsg());
                }
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public void loadBanners(Lifecycle lifecycle) {
        repository.queryBanners(lifecycle, new BaseRepository.QueryCallBack<Result<List<Banner>>>() {
            @Override
            public void onSuccess(Result<List<Banner>> entity) {
                if (getViewControlHelper().isLoadSuccess(entity.getErrorCode())) {
                    banner.setValue(entity.getData());
                } else {
                    showToast(entity.getErrorMsg());
                }
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                getViewControlHelper().onLoadFailed(e);
            }
        });
    }

    public void initHomeLiveData(Lifecycle lifecycle) {
        homeLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pagerHome)
                , lifecycle);
    }

    public void initProjectLiveData(Lifecycle lifecycle) {
        projectLiveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pagerLatestProject)
                , lifecycle);
    }
}
