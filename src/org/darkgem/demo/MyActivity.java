package org.darkgem.demo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.example.myapp.R;
import org.darkgem.view.badgeview.BadgeView;

public class MyActivity extends Activity {
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final BadgeView bv_badge_view = (BadgeView) findViewById(R.id.bv_badge_view);
        bv_badge_view.badge();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bv_badge_view.badge(BitmapFactory.decodeResource(getResources(), R.drawable.add));
            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bv_badge_view.badge(10);
            }
        }, 4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bv_badge_view.badge(1000);
            }
        }, 6000);
    }
}
