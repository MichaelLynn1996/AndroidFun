package xyz.sealynn.androidfun.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by SeaLynn0 on 2019/1/3 20:47
 * <p>
 * Email：sealynndev@gmail.com
 */
public class ActivityUtils {

    private ActivityUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void startActivity(Context packageContext, Class<?> cls) {
        packageContext.startActivity(new Intent(packageContext, cls));
    }

    public static void startActivityForResult(@RequiresPermission Intent intent, int requestCode,
                                              @Nullable Bundle options){

    }

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().add(frameId, fragment).commit();
    }

    public static void addFragmentToActivityBackStack(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void startActivity(Context packageContext, Class<?> cls, Bundle bundle) {
        packageContext.startActivity(new Intent(packageContext, cls), bundle);
    }

    public static void replaceFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId){
        fragmentManager.beginTransaction().replace(frameId,fragment).show(fragment).commit();
    }
}
