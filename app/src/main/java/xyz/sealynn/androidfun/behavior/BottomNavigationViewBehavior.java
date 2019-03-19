package xyz.sealynn.androidfun.behavior;

import android.content.Context;
import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Objects;

/**
 * Created by SeaLynn0 on 2018/9/5 17:41
 * <p>
 * Email：sealynndev@gmail.com
 */
public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {

    public BottomNavigationViewBehavior() {
    }

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {

        ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).topMargin = parent.getMeasuredHeight() - child.getMeasuredHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        //因为Behavior只对CoordinatorLayout的直接子View生效，因此将依赖关系转移到AppBarLayout
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        //得到依赖View的滑动距离
        int top = ((AppBarLayout.Behavior)Objects.requireNonNull(((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior())).getTopAndBottomOffset();
        //因为BottomNavigation的滑动与ToolBar是反向的，所以取-top值
//        ViewCompat.setTranslationY(child, -top);  //@Deprecated
        ViewCompat.animate(child).translationY(-top);
        return false;
    }
}
