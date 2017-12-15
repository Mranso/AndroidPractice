package com.anso.androidpractice;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Button frameAnimationStartView, frameAnimationStopView, tweenAnimationView, propertyAnimationView;
    private ImageView frameAnimationImage;

    private Animation translateAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initListener();
    }

    private void initListener() {

        /*
        * 应用场景
        * 较为复杂的个性化动画效果
        * 例如 Loading 加载的动图
        */
        frameAnimationStartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、使用简单方便
                //2、使用大图容易引起OOM，使用尽量小的图片
                //3、最好在XML中定义，方便重用，可读性强
                frameAnimationImage.setImageResource(R.drawable.frame_animation_drawable);
                AnimationDrawable animationDrawable = (AnimationDrawable) frameAnimationImage.getDrawable();
                animationDrawable.start();


//                //Java代码实现帧动画
//                AnimationDrawable animation = new AnimationDrawable();
//                //第一个参数为动画图片资源，第二个参数为执行时间
//                animation.addFrame(ContextCompat.getDrawable(context,R.drawable.frame_animation_a), 200);
//                animation.addFrame(ContextCompat.getDrawable(context,R.drawable.frame_animation_b), 200);
//                animation.addFrame(ContextCompat.getDrawable(context,R.drawable.frame_animation_c), 200);
//                animation.setOneShot(false);
//                frameAnimationImage.setImageDrawable(animation);
//                animation.stop();
//                // 特别注意：在动画start()之前要先stop()，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次
//                animation.start();
            }
        });

        frameAnimationStopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable animationDrawable = (AnimationDrawable) frameAnimationImage.getDrawable();
                animationDrawable.stop();
            }
        });


        /*
        * 应用场景
        * Activity切换效果
        * Fragment切换效果
        * 视图组（ViewGroup）中子元素的出场效果
        */
        tweenAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                平移 translateAnimation
//                XML实现动画
                translateAnimation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
                tweenAnimationView.startAnimation(translateAnimation);

//                Java代码实现动画
//                Animation translate = new TranslateAnimation(0, 500, 0, 500);
//                创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
//                参数分别是：
//                1. fromXDelta ：视图在水平方向x 移动的起始值
//                2. toXDelta ：视图在水平方向x 移动的结束值
//                3. fromYDelta ：视图在竖直方向y 移动的起始值
//                4. toYDelta：视图在竖直方向y 移动的结束值
//                设置系统自带插值器
//                Interpolator overshootInterpolator = new OvershootInterpolator();
//                translate.setInterpolator(overshootInterpolator);
//                translate.setDuration(3000);
//                固定属性的设置都是在其属性前加“set”，如setDuration（）
//                属性参考 R.anim.translate_animation
//                tweenAnimationView.startAnimation(translate);

//===================================================================================================================
//                缩放动画  ScaleAnimation

//                Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_animation);
//                tweenAnimationView.startAnimation(scaleAnimation);


//                Animation scale = new ScaleAnimation(0, 2, 0, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                创建缩放动画的对象 & 设置动画效果：缩放动画对应的Animation子类为RotateAnimation
//                参数说明:
//                1. fromX ：动画在水平方向X的结束缩放倍数
//                2. toX ：动画在水平方向X的结束缩放倍数
//                3. fromY ：动画开始前在竖直方向Y的起始缩放倍数
//                4. toY：动画在竖直方向Y的结束缩放倍数
//                5. pivotXType:缩放轴点的x坐标的模式
//                6. pivotXValue:缩放轴点x坐标的相对值
//                7. pivotYType:缩放轴点的y坐标的模式
//                8. pivotYValue:缩放轴点y坐标的相对值
//                pivotXType = Animation.ABSOLUTE:缩放轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
//                pivotXType = Animation.RELATIVE_TO_SELF:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
//                pivotXType = Animation.RELATIVE_TO_PARENT:缩放轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
//                scale.setDuration(3000);
//                固定属性的设置都是在其属性前加“set”，如setDuration（）
//                tweenAnimationView.startAnimation(scale);

//===================================================================================================================
//                旋转动画 RotateAnimation
//                Animation rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation);
//                tweenAnimationView.startAnimation(rotateAnimation);


//                Animation rotate = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                创建旋转动画的对象 & 设置动画效果：旋转动画对应的Animation子类为RotateAnimation
//                参数说明:
//                1. fromDegrees ：动画开始时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
//                2. toDegrees ：动画结束时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
//                3. pivotXType：旋转轴点的x坐标的模式
//                4. pivotXValue：旋转轴点x坐标的相对值
//                5. pivotYType：旋转轴点的y坐标的模式
//                6. pivotYValue：旋转轴点y坐标的相对值
//                pivotXType = Animation.ABSOLUTE:旋转轴点的x坐标 =  View左上角的原点 在x方向 加上 pivotXValue数值的点(y方向同理)
//                pivotXType = Animation.RELATIVE_TO_SELF:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 自身宽度乘上pivotXValue数值的值(y方向同理)
//                pivotXType = Animation.RELATIVE_TO_PARENT:旋转轴点的x坐标 = View左上角的原点 在x方向 加上 父控件宽度乘上pivotXValue数值的值 (y方向同理)
//                rotate.setDuration(3000);
//                固定属性的设置都是在其属性前加“set”，如setDuration（）
//                tweenAnimationView.startAnimation(rotate);

//===================================================================================================================
//                渐变动画  AlphaAnimation
//                Animation alphaAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_animation);
//                tweenAnimationView.startAnimation(alphaAnimation);


//                Animation alpha = new AlphaAnimation(1, 0);
//                创建透明度动画的对象 & 设置动画效果：透明度动画对应的Animation子类为AlphaAnimation
//                参数说明:
//                1. fromAlpha:动画开始时视图的透明度(取值范围: -1 ~ 1)
//                2. toAlpha:动画结束时视图的透明度(取值范围: -1 ~ 1)
//                alpha.setDuration(3000);
//                固定属性的设置都是在其属性前加“set”，如setDuration（）
//                tweenAnimationView.startAnimation(alpha);

//===================================================================================================================
//                组合动画 SetAnimation
//                Animation setAnimation = AnimationUtils.loadAnimation(context, R.anim.set_animation);
//                tweenAnimationView.startAnimation(setAnimation);


//                组合动画设置
//                AnimationSet setAnimation = new AnimationSet(true);
//                步骤1:创建组合动画对象(设置为true)
//                步骤2:设置组合动画的属性
//                特别说明以下情况
//                因为在下面的旋转动画设置了无限循环(RepeatCount = INFINITE)
//                所以动画不会结束，而是无限循环
//                所以组合动画的下面两行设置是无效的
//                setAnimation.setRepeatMode(Animation.RESTART);
//                setAnimation.setRepeatCount(1);// 设置了循环一次,但无效
//
//                步骤3:逐个创建子动画(方式同单个动画创建方式,此处不作过多描述)
//
//                子动画1:旋转动画
//                Animation rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                rotate.setDuration(1000);
//                rotate.setRepeatMode(Animation.RESTART);
//                rotate.setRepeatCount(Animation.INFINITE);
//
//                子动画2:平移动画
//                Animation translate = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT,-0.5f,
//                        TranslateAnimation.RELATIVE_TO_PARENT,0.5f,
//                        TranslateAnimation.RELATIVE_TO_SELF,0
//                        ,TranslateAnimation.RELATIVE_TO_SELF,0);
//                translate.setDuration(10000);
//
//                子动画3:透明度动画
//                Animation alpha = new AlphaAnimation(1,0);
//                alpha.setDuration(3000);
//                alpha.setStartOffset(7000);
//
//                子动画4:缩放动画
//                Animation scale = new ScaleAnimation(1,0.5f,1,0.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                scale.setDuration(1000);
//                scale.setStartOffset(4000);
//
//                步骤4:将创建的子动画添加到组合动画里
//                setAnimation.addAnimation(alpha);
//                setAnimation.addAnimation(rotate);
//                setAnimation.addAnimation(translate);
//                setAnimation.addAnimation(scale);
//                tweenAnimationView.startAnimation(setAnimation);
            }
        });

        /*
        * 补间动画的监听
        * */
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//              开始
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//              结束
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//              重复执行
            }
        });



        propertyAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        frameAnimationStartView = (Button) findViewById(R.id.animation_frame_start);
        frameAnimationStopView = (Button) findViewById(R.id.animation_frame_stop);
        tweenAnimationView = (Button) findViewById(R.id.animation_tween);
        propertyAnimationView = (Button) findViewById(R.id.animation_property);
        frameAnimationImage = (ImageView) findViewById(R.id.animation_frame_image);

        translateAnimation = AnimationUtils.loadAnimation(context, R.anim.translate_animation);
    }
}
