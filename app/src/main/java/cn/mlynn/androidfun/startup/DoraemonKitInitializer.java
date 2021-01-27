package cn.mlynn.androidfun.startup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.didichuxing.doraemonkit.DoraemonKit;

import java.util.Collections;
import java.util.List;

import cn.mlynn.androidfun.APP;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.startup
 * @ClassName: DoraemonKitInitializer
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/8/25 23:43
 */
public class DoraemonKitInitializer implements Initializer<DoraemonKit> {
    @NonNull
    @Override
    public DoraemonKit create(@NonNull Context context) {
        DoraemonKit.install((APP)context.getApplicationContext());
        return DoraemonKit.INSTANCE;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
