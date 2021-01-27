/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.sign
 * @ClassName: SignViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/16 23:31
 */
package cn.mlynn.androidfun.module.sign;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.SavedStateHandle;

import cn.mlynn.androidfun.base.BaseRepository;
import cn.mlynn.androidfun.base.BaseViewModel;
import cn.mlynn.androidfun.model.wan.Result;
import cn.mlynn.androidfun.model.wan.User;
import io.reactivex.rxjava3.annotations.NonNull;

public class SignViewModel extends BaseViewModel {

    SignRepository repository;

    @ViewModelInject
    public SignViewModel(SignRepository repository, @Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
        this.repository = repository;
    }

    public void login(Lifecycle lifecycle, String u, String p) {
        repository.login(lifecycle, u, p, new BaseRepository.QueryCallBack<Result<User>>() {
            @Override
            public void onSuccess(Result<User> entity) {

            }

            @Override
            public void onFailure(@NonNull Throwable e) {

            }
        });
    }
}
