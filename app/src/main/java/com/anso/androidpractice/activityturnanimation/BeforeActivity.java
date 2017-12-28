package com.anso.androidpractice.activityturnanimation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.anso.androidpractice.R;

public class BeforeActivity extends AppCompatActivity {

    private Context context;
    private ImageView imageView;
    private CardView cardView;
    private ObjectAnimator animator1, animator2;

    private float DownX, DownY, moveX, moveY;
    private long currentMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
        context = this;
        initView();
        initListener();
    }

    private void initListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        DownX = event.getX();//float DownX
                        DownY = event.getY();//float DownY
                        moveX = 0;
                        moveY = 0;
                        currentMS = System.currentTimeMillis();


                        animator1 = ObjectAnimator.ofFloat(cardView, "scaleX", 1f, 0.96f);
                        animator2 = ObjectAnimator.ofFloat(cardView, "scaleY", 1f, 0.96f);
                        animator1.setDuration(300);
                        animator2.setDuration(300);
                        animator1.start();
                        animator2.start();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX += Math.abs(event.getX() - DownX);//X轴距离
                        moveY += Math.abs(event.getY() - DownY);//y轴距离
                        DownX = event.getX();
                        DownY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        long moveTime = System.currentTimeMillis() - currentMS;
                        long animationTime;
                        if (400 - moveTime <= 0) {
                            animationTime = 0;
                        } else {
                            animationTime = 400 - moveTime;
                        }

                        if ((moveX > 20 || moveY > 20)) {
                            animator2.reverse();
                            animator1.reverse();
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    AfterActivity.start(BeforeActivity.this, imageView);
                                    animator2.reverse();
                                    animator1.reverse();
                                }
                            }, animationTime);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.before_image);
        cardView = (CardView) findViewById(R.id.before_card_view);
    }
}
