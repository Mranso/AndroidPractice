package com.anso.androidpractice.activityturnanimation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.animation.SpringAnimation;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.anso.androidpractice.R;

public class AfterActivity extends AppCompatActivity {

    private ImageView imageView;
    private Context context;

    public static void start(Activity activity, View view) {
        Intent intent = new Intent(activity, AfterActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "TestTransition");
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);
        context = this;
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SpringAnimation animY = new SpringAnimation(imageView, SpringAnimation.TRANSLATION_Y, 0);
                animY.getSpring().setStiffness(50);
                animY.getSpring().setDampingRatio((float) 0.5);
                animY.setStartVelocity(-350);
                animY.start();
            }
        }, 100);
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.after_image);
    }
}
