package cn.mlynn.androidfun.module.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.FragmentRegisterBinding;

public class RegisterFragment extends BaseFragment<SignViewModel, FragmentRegisterBinding> {

    @Override
    protected void init(Bundle savedInstanceState) {
        getBinding().btGo.setOnClickListener(view -> {
            if (getBinding().etUsername.getText() != null
                    && getBinding().etPassword.getText() != null
                    && getBinding().etRepeatPassword.getText() != null) {
                String u = getBinding().etUsername.getText().toString().trim();
                String p = getBinding().etPassword.getText().toString().trim();
                String rp = getBinding().etRepeatPassword.getText().toString().trim();
                if (u.isEmpty()) {
                    getBinding().tilUsername.setErrorEnabled(true);
                    getBinding().tilUsername.setError(getText(R.string.USERNAME_CANT_BE_NUL));
                }
                if (p.isEmpty()) {
                    getBinding().tilPassword.setErrorEnabled(true);
                    getBinding().tilPassword.setError(getText(R.string.PASSWORD_CANT_BE_NUL));
                }
                if (rp.isEmpty()) {
                    getBinding().tilRepeatPassword.setErrorEnabled(true);
                    getBinding().tilRepeatPassword.setError(getText(R.string.PASSWORD_CANT_BE_NUL));
                } else if (!p.equals(rp)) {
                    getBinding().tilRepeatPassword.setErrorEnabled(true);
                    getBinding().tilRepeatPassword.setError(getText(R.string.PASS_AND_REPASS_IS_NOT_EQUAL));
                } else if (!u.isEmpty() && !p.isEmpty() && !rp.isEmpty()) {
//                    getPresenter().register(u, p, rp);
                    //TODO  RegisterAction
                    freeze();
                }
            }
        });
        getBinding().etUsername.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && getBinding().tilUsername.isErrorEnabled())
                getBinding().tilUsername.setErrorEnabled(false);
        });
        getBinding().etPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && getBinding().tilPassword.isErrorEnabled())
                getBinding().tilPassword.setErrorEnabled(false);
        });
        getBinding().etRepeatPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && getBinding().tilRepeatPassword.isErrorEnabled())
                getBinding().tilRepeatPassword.setErrorEnabled(false);
        });
    }

    @Override
    protected FragmentRegisterBinding initBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return FragmentRegisterBinding.inflate(inflater, container, false);
    }

//    @Override
    public void freeze() {
        getBinding().etUsername.setEnabled(false);
        getBinding().etPassword.setEnabled(false);
        getBinding().btGo.setEnabled(false);
        getBinding().etRepeatPassword.setEnabled(false);
    }

//    @Override
    public void unFreeze() {
        getBinding().etUsername.setEnabled(true);
        getBinding().etPassword.setEnabled(true);
        getBinding().btGo.setEnabled(true);
        getBinding().etRepeatPassword.setEnabled(true);
    }

    @Override
    protected void dismissLoading() {

    }

    @Override
    protected void startLoading() {

    }

    @Override
    protected SignViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(SignViewModel.class);
    }
}
