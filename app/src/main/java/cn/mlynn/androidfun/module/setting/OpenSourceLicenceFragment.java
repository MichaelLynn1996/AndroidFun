package cn.mlynn.androidfun.module.setting;

import android.content.Context;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by SeaLynn0 on 2019/1/15 19:43
 * <p>
 * Email：sealynndev@gmail.com
 */
public class OpenSourceLicenceFragment extends MaterialAboutFragment {
    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        return new MaterialAboutList.Builder()
                .build();
    }
}
