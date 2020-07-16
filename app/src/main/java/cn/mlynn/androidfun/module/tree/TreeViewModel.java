/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.knowledgetree
 * @ClassName: TreeViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/5 0:22
 */
package cn.mlynn.androidfun.module.tree;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;

import java.util.List;

import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Children;

public class TreeViewModel extends BaseViewModel {

    private TreeRepository repository = new TreeRepository();

    private LiveData<List<Children>> treeLiveData;

    private boolean isFirstLoaded = false;

    public TreeViewModel() {
        treeLiveData = repository.getChildrenLiveData();
    }

    public LiveData<List<Children>> getTreeLiveData() {
        return treeLiveData;
    }

    public void loadTree(Lifecycle lifecycle) {
        repository.queryTree(lifecycle, getViewControlHelper());
    }

    public boolean isFirstLoaded() {
        return isFirstLoaded;
    }

    public void setFirstLoaded(boolean firstLoaded) {
        isFirstLoaded = firstLoaded;
    }
}
