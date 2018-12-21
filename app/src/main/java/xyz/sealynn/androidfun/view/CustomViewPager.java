package xyz.sealynn.androidfun.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by SeaLynn0 on 2018/10/10 1:02
 * <p>
 * Email：sealynndev@gmail.com
 */
public class CustomViewPager extends ViewPager {

    private boolean isAbleScrolling = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     * @param ableScroll false 不能换页， true 可以滑动换页
     */
    public void setScrollingEnable(boolean ableScroll) {
        this.isAbleScrolling = ableScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isAbleScrolling && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isAbleScrolling && super.onTouchEvent(ev);

    }

}
