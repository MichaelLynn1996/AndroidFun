package xyz.sealynn.androidfun.module;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import xyz.sealynn.androidfun.R;

/**
 * Created by SeaLynn0 on 2018/10/13 2:54
 * <p>
 * Email：sealynndev@gmail.com
 */
public class AboutActivity extends MaterialAboutActivity {
    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        return new MaterialAboutList.Builder()
                .build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }


}
