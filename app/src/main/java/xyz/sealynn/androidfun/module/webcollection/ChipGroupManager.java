package xyz.sealynn.androidfun.module.webcollection;

import android.content.Context;
import android.view.View;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xyz.sealynn.androidfun.R;
import xyz.sealynn.androidfun.model.Result;
import xyz.sealynn.androidfun.model.WebCollection;
import xyz.sealynn.androidfun.net.RetrofitManager;
import xyz.sealynn.androidfun.utils.ActivityUtils;
import xyz.sealynn.androidfun.utils.ToastUtils;

/**
 * Created by MichaelLynn on 2019/4/21 1:18
 * <p>
 * Email: sealynndev@gamil.com
 */
public class ChipGroupManager {

    private ChipGroup group;

    private Map<Chip, WebCollection> collectionChipMap = new HashMap<>();

    private Context context;

    private Chip addChip;

    private Boolean isAllChipCloseIconVisible = false;

    public ChipGroupManager(ChipGroup chipGroup, Context context) {
        this.group = chipGroup;
        this.context = context;

        addChip = new Chip(context);
        addChip.setText(context.getString(R.string.add));
        addChip.setChipIcon(context.getDrawable(R.drawable.ic_action_add));
        addChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void addChip(Chip chip, WebCollection collection) {
        chip.setText(collection.getName());
        chip.setOnClickListener(v -> {
            ActivityUtils.startWebActivity(context, collection.getLink());
        });
        chip.setOnLongClickListener(v -> {
            chip.setCloseIconVisible(true);
            return false;
        });
        group.addView(chip);
        collectionChipMap.put(chip, collection);
    }

    public void removeChip(Chip chip) {
        collectionChipMap.remove(chip);
        group.removeView(chip);
    }

    public void showAllChipCloseIcon() {
        for (Map.Entry<Chip, WebCollection> entry : collectionChipMap.entrySet()) {
            Chip chip = entry.getKey();
            chip.setCloseIconVisible(true);
            chip.setOnCloseIconClickListener(v -> RetrofitManager
                    .getInstance()
                    .createReq()
                    .deleteTool(entry.getValue().getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Result>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Result t) {
                            if (t.getErrorCode() == 0) {
                                removeChip(chip);
                                ToastUtils.shortToast(context, context.getString(R.string.delete_succ));
                            } else ToastUtils.shortToast(context, t.getErrorMsg());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }

                    }));
        }
        group.addView(addChip);
        isAllChipCloseIconVisible = true;
    }

    /**
     * 关闭所有Chip的可编辑状态
     */
    public void disableAllChipCloseIcon() {
        for (Map.Entry<Chip, WebCollection> entry : collectionChipMap.entrySet()) {
            Chip chip = entry.getKey();
            chip.setCloseIconVisible(false);
        }
        group.removeView(addChip);
        isAllChipCloseIconVisible = false;
    }

    /**
     *
     * @return 所有的Chip是否为可编辑状态
     */
    public Boolean isAllChipCloseIconVisible() {
        return isAllChipCloseIconVisible;
    }
}
