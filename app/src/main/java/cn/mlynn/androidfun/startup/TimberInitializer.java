package cn.mlynn.androidfun.startup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.Collections;
import java.util.List;

import cn.mlynn.androidfun.APP;
import cn.mlynn.androidfun.BuildConfig;
import kotlin.Unit;
import timber.log.Timber;

/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.startup
 * @ClassName: TimberInitializer
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/8/26 0:11
 */
public class TimberInitializer implements Initializer<Unit> {
    @NonNull
    @Override
    public Unit create(@NonNull Context context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
        return Unit.INSTANCE;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {

        }
    }
}
