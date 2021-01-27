package cn.mlynn.androidfun.module.wx;

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
import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.net.RequestApi;
import io.reactivex.rxjava3.annotations.NonNull;

public class WxViewModel extends BaseViewModel {

    private WxRepository repository;

    private MutableLiveData<List<Chapter>> chapterLiveData;

    @ViewModelInject
    public WxViewModel(@Assisted SavedStateHandle savedStateHandle, WxRepository repository) {
        super(savedStateHandle);
        this.repository = repository;
        chapterLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Chapter>> getChapterLiveData() {
        return chapterLiveData;
    }

    public LiveData<PagingData<Article>> getWxArticleLiveData(Lifecycle lifecycle, int id) {
        return repository.initWxArticleLiveData(lifecycle, id);
    }

    public void queryWxChapters(Lifecycle lifecycle) {
        repository.queryWxChapter(lifecycle, new BaseRepository.QueryCallBack<Result<List<Chapter>>>() {
            @Override
            public void onSuccess(Result<List<Chapter>> entity) {
                if (entity.getErrorCode() == RequestApi.SUCC)
                    chapterLiveData.setValue(entity.getData());
                else showToast(entity.getErrorMsg());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public LiveData<PagingData<Article>> getWxSearchResultLiveData(Lifecycle lifecycle, int id, String k) {
        return repository.initWxSearchResultLiveData(lifecycle, id, k);
    }
}