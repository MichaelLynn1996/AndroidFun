package cn.mlynn.androidfun.module.sign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import cn.mlynn.androidfun.R;
import cn.mlynn.androidfun.base.BaseFragment;
import cn.mlynn.androidfun.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment<SignViewModel, FragmentLoginBinding> {

    public static final int REQUEST_REGISTER = 1;

    @Override
    protected void init(Bundle savedInstanceState) {
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
                    getViewModel().login(getLifecycle(), u, p);
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
    }

    @Override
    protected FragmentLoginBinding initBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
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

    public void freeze() {
        getBinding().etUsername.setEnabled(false);
        getBinding().etPassword.setEnabled(false);
        getBinding().btGo.setEnabled(false);
        getBinding().fab.setEnabled(false);
    }

    public void unFreeze() {
        getBinding().etUsername.setEnabled(true);
        getBinding().etPassword.setEnabled(true);
        getBinding().btGo.setEnabled(true);
        getBinding().fab.setEnabled(true);
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
