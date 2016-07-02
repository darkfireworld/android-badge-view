package org.darkgem.view.badgeview;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * BadgeView
 */
public class BadgeView extends FrameLayout {
    //红色背景
    Drawable bg_round;
    //红点
    View v_point;
    //计数
    TextView tv_count;
    //图片
    ImageView iv_label;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * dp转 px.
     *
     * @param value the value
     * @return the int
     */
    public static int dp2px(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    private void init() {
        //红色背景
        {
            ShapeDrawable drawable = new ShapeDrawable(new Shape() {
                @Override
                public void draw(Canvas canvas, Paint paint) {
                    canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);
                }
            });
            drawable.getPaint().setColor(Color.parseColor("#FF3030"));
            bg_round = drawable;
        }
        //红点
        {
            v_point = new View(getContext());
            v_point.setLayoutParams(new FrameLayout.LayoutParams(dp2px(getContext(), 10), dp2px(getContext(), 10), Gravity.CENTER));
            v_point.setBackgroundDrawable(bg_round);
            addView(v_point);
        }
        //数字
        {
            tv_count = new TextView(getContext());
            tv_count.setLayoutParams(new FrameLayout.LayoutParams(dp2px(getContext(), 20), dp2px(getContext(), 20), Gravity.CENTER));
            tv_count.setBackgroundDrawable(bg_round);
            tv_count.setGravity(Gravity.CENTER);
            tv_count.setTextSize(12);
            tv_count.setTextColor(Color.parseColor("#FFFFFF"));
            addView(tv_count);
        }
        //图片
        {
            iv_label = new ImageView(getContext());
            iv_label.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
            addView(iv_label);
        }
        //重置
        reset();
    }

    /**
     * 印记-红点
     */
    public void badge() {
        reset();
        v_point.setVisibility(VISIBLE);
    }

    /**
     * 印记-资源
     */
    public void badge(Bitmap bitmap) {
        reset();
        if (bitmap != null) {
            iv_label.setImageBitmap(bitmap);
            iv_label.setVisibility(VISIBLE);
        }
    }

    /**
     * 印记-文字, 如果数字<=0，则隐藏
     */
    public void badge(int count) {
        reset();
        if (count > 0) {
            String text = String.format("%d", count);
            if (count >= 100) {
                text = "…";
            }
            tv_count.setText(text);
            tv_count.setVisibility(VISIBLE);
        }
    }


    /**
     * 隐藏所有的组件
     */
    public void reset() {
        v_point.setVisibility(GONE);
        tv_count.setVisibility(GONE);
        iv_label.setVisibility(GONE);
    }
}
