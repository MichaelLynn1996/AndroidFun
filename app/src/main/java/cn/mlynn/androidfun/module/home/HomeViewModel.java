/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.module.home2
 * @ClassName: HomeFragment
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/7 0:45
 */
package cn.mlynn.androidfun.module.home;

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
import cn.mlynn.androidfun.model.wan.Article;
import cn.mlynn.androidfun.model.wan.Banner;
import cn.mlynn.androidfun.model.wan.Result;
import io.reactivex.rxjava3.annotations.NonNull;

public class HomeViewModel extends BaseViewModel {

    private HomeRepository repository;

    private MutableLiveData<List<Banner>> banner;

    private MutableLiveData<List<Article>> topLiveData;
    private LiveData<PagingData<Article>> homeLiveData;

    @ViewModelInject
    public HomeViewModel(HomeRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
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
        homeLiveData = repository.initHomeLiveData(lifecycle);
    }
}
