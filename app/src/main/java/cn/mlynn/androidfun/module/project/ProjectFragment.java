package cn.mlynn.androidfun.module.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.FramentProjectBinding;

/**
 * Created by SeaLynn0 on 2018/12/6 20:19
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ProjectFragment extends BaseFragment<ProjectViewModel, FramentProjectBinding> {

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected FramentProjectBinding initBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return FramentProjectBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void dismissLoading() {

    }

    @Override
    protected void startLoading() {

    }

    @Override
    protected ProjectViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);
    }
}
