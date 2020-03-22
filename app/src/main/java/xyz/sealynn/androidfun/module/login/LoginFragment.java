package xyz.sealynn.androidfun.module.login;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.base.BaseFragment;
import xyz.sealynn.androidfun.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment<LoginContract.Presenter, FragmentLoginBinding> implements LoginContract.View {

    public static final int REQUEST_REGISTER = 1;

    @Override
    protected FragmentLoginBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void prepareData(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initEvent() {
        getBinding().btGo.setOnClickListener(view -> {
            if (getBinding().etUsername.getText() != null && getBinding().etPassword.getText() != null) {
                String u = getBinding().etUsername.getText().toString().trim();
                String p = getBinding().etPassword.getText().toString().trim();
                if (u.isEmpty()) {
                    getBinding().tilUsername.setErrorEnabled(true);
                    getBinding().tilUsername.setError(getText(R.string.USERNAME_CANT_BE_NUL));
                }
                if (p.isEmpty()) {
                    getBinding().tilPassword.setErrorEnabled(true);
                    getBinding().tilPassword.setError(getText(R.string.PASSWORD_CANT_BE_NUL));
                }
                if (!u.isEmpty() && !p.isEmpty()) {
                    getPresenter().login(u, p);
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
        getBinding().fab.setOnClickListener(view -> {
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(getBinding().etUsername, "etu")
                    .addSharedElement(getBinding().tilUsername, "tilu")
                    .addSharedElement(getBinding().etPassword, "etp")
                    .addSharedElement(getBinding().tilUsername, "tilp")
                    .build();
            Navigation.findNavController(view).navigate(R.id.registerFragment
                    , null, null, extras);
//            int[] vLocation = new int[2];
//            fab.getLocationInWindow(vLocation);
//            centerX = vLocation[0] + fab.getMeasuredWidth() / 2;
//            centerY = vLocation[1] + fab.getMeasuredHeight() / 2;
//            Pair etu = new Pair(getBinding().etUsername, "etu");
//            Pair tilu = new Pair(getBinding().tilUsername, "tilu");
//            Pair etp = new Pair(getBinding().etPassword, "etp");
//            Pair tilp = new Pair(getBinding().tilUsername, "tilp");
//            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//            ActivityOptions options = ActivityOptions
//                    .makeSceneTransitionAnimation(this, etu, tilu, etp, tilp);
//            intent.putExtra("cx",centerX);
//            intent.putExtra("cy",centerY);
//            startActivityForResult(intent, REQUEST_REGISTER, options.toBundle());
//            ActivityUtils.startActivity(this, RegisterActivity.class);
        });
        getBinding().btBack.setOnClickListener(v -> Objects.requireNonNull(getActivity()).finish());
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void freeze() {
        getBinding().etUsername.setEnabled(false);
        getBinding().etPassword.setEnabled(false);
        getBinding().btGo.setEnabled(false);
        getBinding().fab.setEnabled(false);
        getBinding().btBack.setEnabled(false);
    }

    @Override
    public void unFreeze() {
        getBinding().etUsername.setEnabled(true);
        getBinding().etPassword.setEnabled(true);
        getBinding().btGo.setEnabled(true);
        getBinding().fab.setEnabled(true);
        getBinding().btBack.setEnabled(true);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case REQUEST_REGISTER:
//                if (resultCode == RESULT_OK) {
//                    LoginActivity.this.setResult(RESULT_OK, null);
//                    finish();
//                }
//                break;
//        }
//    }
}
