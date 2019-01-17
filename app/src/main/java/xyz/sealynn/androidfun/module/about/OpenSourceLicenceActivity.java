package xyz.sealynn.androidfun.module.about;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by SeaLynn0 on 2019/1/15 19:43
 * <p>
 * Email：sealynndev@gmail.com
 */
public class OpenSourceLicenceActivity extends MaterialAboutActivity {
    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        return new MaterialAboutList.Builder()
                .build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return "开源许可";
    }
}
