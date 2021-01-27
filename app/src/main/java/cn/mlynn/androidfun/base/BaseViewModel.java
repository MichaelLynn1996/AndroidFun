package cn.mlynn.androidfun.base;

import androidx.hilt.Assisted;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import cn.mlynn.androidfun.net.RequestApi;

public abstract class BaseViewModel extends ViewModel implements IViewModelAction {

    private ViewControlHelper helper = new ViewControlHelper();

    protected MutableLiveData<BaseEventAction> actionLiveData;

    private final SavedStateHandle savedStateHandle;

    public BaseViewModel(@Assisted SavedStateHandle savedStateHandle) {
        actionLiveData = new MutableLiveData<>();
        this.savedStateHandle = savedStateHandle;
    }

    @Override
    public void startLoading() {
//        startLoading(null);
        actionLiveData.setValue(new BaseEventAction(BaseEventAction.ACTION.SHOW_LOADING.ordinal()));
    }

    @Override
    public void dismissLoading() {
        actionLiveData.setValue(new BaseEventAction(BaseEventAction.ACTION.DISMISS_LOADING.ordinal()));
    }

    @Override
    public void showToast(String message) {
        BaseEventAction baseActionEvent = new BaseEventAction(BaseEventAction.ACTION.SHOW_TOAST.ordinal());
        baseActionEvent.setMessage(message);
        actionLiveData.setValue(baseActionEvent);
    }

    public ViewControlHelper getViewControlHelper() {
        return helper;
    }

    @Override
    public MutableLiveData<BaseEventAction> getActionLiveData() {
        return actionLiveData;
    }

    public class ViewControlHelper {

        public void onLoadFailed(String msg){
            BaseViewModel.this.showToast(msg);
            BaseViewModel.this.dismissLoading();
        }

        public void onLoadFailed(Throwable e){
            e.printStackTrace();
            BaseViewModel.this.dismissLoading();
        }

        public void startLoading() {
            BaseViewModel.this.startLoading();
        }

        public void dismissLoading() {
            BaseViewModel.this.dismissLoading();
        }

        public boolean isLoadSuccess(int errorCode) {
            return errorCode == RequestApi.SUCC;
        }
    }

    public SavedStateHandle getSavedStateHandle() {
        return savedStateHandle;
    }
}
