/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.module.project
 * @ClassName: ProjectViewModel
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/5 1:07
 */
package cn.mlynn.androidfun.module.project;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;

import cn.mlynn.androidfun.base.BaseViewModel;

public class ProjectViewModel extends BaseViewModel {

    @ViewModelInject
    public ProjectViewModel(@Assisted SavedStateHandle savedStateHandle) {
        super(savedStateHandle);
    }
}
