/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.square
 * @ClassName: SquareViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/10 21:34
 */
package cn.mlynn.androidfun.module.square;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.paging.PagingData;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Article;

public class SquareViewModel extends BaseViewModel {

    private SquareRepository repository;

    private LiveData<PagingData<Article>> squareLiveData;

    @ViewModelInject
    public SquareViewModel(SquareRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
    }

    public LiveData<PagingData<Article>> getSquareLiveData() {
        if (squareLiveData == null){
            throw new RuntimeException("squareLiveData not been initialized");
        }
        return squareLiveData;
    }

    public void initSquareLiveData(Lifecycle lifecycle) {
        squareLiveData = repository.initSquareLiveData(lifecycle);
    }
}
