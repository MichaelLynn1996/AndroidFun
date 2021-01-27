/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.knowledgetree
 * @ClassName: TreeViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/5 0:22
 */
package cn.mlynn.androidfun.module.tree;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.List;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Chapter;
import cn.mlynn.androidfun.model.wan.Result;
import io.reactivex.rxjava3.annotations.NonNull;

public class TreeViewModel extends BaseViewModel {

    private TreeRepository repository;

    private MutableLiveData<List<Chapter>> treeLiveData;

    @ViewModelInject
    public TreeViewModel(TreeRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
        this.treeLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Chapter>> getTreeLiveData() {
        return treeLiveData;
    }

    public void loadTree(Lifecycle lifecycle) {
        repository.queryTree(lifecycle, new BaseRepository.QueryCallBack<Result<List<Chapter>>>() {
            @Override
            public void onSuccess(Result<List<Chapter>> entity) {
                if (getViewControlHelper().isLoadSuccess(entity.getErrorCode())) {
                    treeLiveData.setValue(entity.getData());
                    getViewControlHelper().dismissLoading();
                }else   getViewControlHelper().onLoadFailed(entity.getErrorMsg());
            }

            @Override
            public void onFailure(@NonNull Throwable e) {
                getViewControlHelper().onLoadFailed(e);
            }
        });
    }
}
