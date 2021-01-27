package cn.mlynn.androidfun.base;

import androidx.lifecycle.MutableLiveData;

/**
 * @ProjectName: AndroidFun
 * @Package: xyz.sealynn.androidfun.base2
 * @ClassName: IViewModelAction
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/6/10 13:26
 */
interface IViewModelAction {

    void startLoading();

    void dismissLoading();

    void showToast(String message);

    MutableLiveData<BaseEventAction> getActionLiveData();
}
