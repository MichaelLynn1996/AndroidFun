/**
 * @ProjectName: AndroidFun
 * @Package: cn.mlynn.androidfun.itemdecoration
 * @ClassName: TitleDecoration
 * @Description: //TODO
 * @Author: Michael Lynn
 * @CreateDate: 2020/7/11 23:16
 */
package cn.mlynn.androidfun.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.mlynn.androidfun.R;

public class TitleDecoration extends RecyclerView.ItemDecoration {

    private String title;

    private Paint mPaint;

    public TitleDecoration(String title) {
        this.title = title;
        this.mPaint = new Paint();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        mPaint.setColor(parent.getContext().getResources().getColor(R.color.colorPrimaryDark));
        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            if (i % 5 == 0) {
                View child = parent.getChildAt(i);
                int left = 0;
                int top = child.getTop() - 50;
                int right = child.getRight();
                int bottom = child.getTop();
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position == 0)
            outRect.set(0, 50, 0, 0);
    }
}
