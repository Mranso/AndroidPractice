package com.anso.androidpractice;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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

public class AnimationActivity extends AppCompatActivity {

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
        *
        *
        * 【缺点】：
        * a. 作用对象局限：View
        * 即补间动画 只能够作用在视图View上，即只可以对一个Button、TextView、甚至是LinearLayout、或者其它继承自View的组件进行动画操作，但无法对非View的对象进行动画操作
        * 有些情况下的动画效果只是视图的某个属性 & 对象而不是整个视图；
        * 如，现需要实现视图的颜色动态变化，那么就需要操作视图的颜色属性从而实现动画效果，而不是针对整个视图进行动画操作
        *
        * b. 没有改变View的属性，只是改变视觉效果
        * 补间动画只是改变了View的视觉效果，而不会真正去改变View的属性。
        * 如，将屏幕左上角的按钮 通过补间动画 移动到屏幕的右下角
        * 点击当前按钮位置（屏幕右下角）是没有效果的，因为实际上按钮还是停留在屏幕左上角，补间动画只是将这个按钮绘制到屏幕右下角，改变了视觉效果而已。
        *
        * c. 动画效果单一
        * 补间动画只能实现平移、旋转、缩放 & 透明度这些简单的动画需求
        * 一旦遇到相对复杂的动画效果，即超出了上述4种动画效果，那么补间动画则无法实现。
        * 即在功能 & 可扩展性有较大局限性
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


        /*
        * 属性动画（Android 3.0  API 11之后的新特性） 建议使用Java代码来实现属性动画
        * 针对于任何Java对象 【不再局限于 视图View对象】
        * 可自定义各种动画效果【不再局限于4种基本变换：平移、旋转、缩放 & 透明度】
        * 重要的两个类 ValueAnimator & ObjectAnimator
        * 【原理】在一定时间间隔内，通过不断对值进行改变，并不断将该值赋给对象的属性，从而实现该对象在该属性上的动画效果
        * */
        propertyAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * ValueAnimator类
                * 定义：属性动画机制中 最核心的一个类
                * 实现动画的原理：通过不断控制 值 的变化，再不断 手动 赋给对象的属性，从而实现动画效果
                *
                * ValueAnimator类中有3个重要方法：
                * ValueAnimator.ofInt（int values） 将初始值 以整型数值的形式 过渡到结束值  【估值器是整型估值器 - IntEvaluator】
                * ValueAnimator.ofFloat（float values）
                * ValueAnimator.ofObject（int values）
                *
                * ValueAnimator.ofInt（）与ValueAnimator.oFloat（）仅仅只是在估值器上的区别：（即如何从初始值 过渡 到结束值）
                * ValueAnimator.oFloat（）采用默认的浮点型估值器 (FloatEvaluator)
                * ValueAnimator.ofInt（）采用默认的整型估值器（IntEvaluator）
                * */

                //Java代码实现
                // 步骤1：设置动画属性的初始值 & 结束值
                ValueAnimator anim = ValueAnimator.ofInt(propertyAnimationView.getLayoutParams().width, 500);
                // ofInt（）作用有两个
                // 1. 创建动画实例
                // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
                // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
                // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值

                // 步骤2：设置动画的播放各种属性
                // 设置动画运行的时长
                anim.setDuration(500);
                // 设置动画延迟播放时间
                anim.setStartDelay(200);
                // 设置动画重复播放次数 = 重放次数+1
                // 动画播放次数 = infinite时,动画无限重复
                anim.setRepeatCount(0);
                // 设置重复播放动画模式
                // ValueAnimator.RESTART(默认):正序重放
                // ValueAnimator.REVERSE:倒序回放
                anim.setRepeatMode(ValueAnimator.RESTART);

                // 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
                // 设置 值的更新监听器  值每次改变、变化一次,该方法就会被调用一次
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        // 获得改变后的值
                        int currentValue = (Integer) animation.getAnimatedValue();
                        // 输出改变后的值
                        System.out.println(currentValue);
                        // 步骤4：将改变后的值赋给对象的属性值，下面会详细说明
                        propertyAnimationView.getLayoutParams().width = currentValue;
                        // 步骤5：刷新视图，即重新绘制，从而实现动画效果
                        propertyAnimationView.requestLayout();
                    }
                });
                anim.start();

                //XML实现
                // 载入XML动画
//                Animator animator = AnimatorInflater.loadAnimator(context, R.animator.value_animator);
                // 设置动画对象
//                animator.setTarget(propertyAnimationView);
                // 启动动画
//                animator.start();


//=======================ValueAnimator.ofObject（）========================================================================
                // 创建初始动画时的对象  & 结束动画时的对象
//                Object object1 = new Object();
//                Object object2 = new Object();
//
//                ValueAnimator animator = ValueAnimator.ofObject(new myObjectEvaluator(), object1, object2);
                // 创建动画对象 & 设置参数
                // 参数说明
                // 参数1：自定义的估值器对象（TypeEvaluator 类型参数）
                // 参数2：初始动画的对象
                // 参数3：结束动画的对象
//                animator.setDuration(5000);
//                animator.start();


//=========================ObjectAnimator类=================================================================

                /*
                * 直接对对象的属性值进行改变操作，从而实现动画效果
                * 如直接改变 View的 alpha 属性 从而实现透明度的动画效果
                * 继承自ValueAnimator类，即底层的动画实现机制是基于ValueAnimator类
                *
                * ObjectAnimator与 ValueAnimator类的区别：
                * ValueAnimator 类是先改变值，然后 手动赋值 给对象的属性从而实现动画；是 间接 对对象属性进行操作；
                * ObjectAnimator 类是先改变值，然后 自动赋值 给对象的属性从而实现动画；是 直接 对对象属性进行操作；
                * */

//                ObjectAnimator animator = ObjectAnimator.ofFloat(propertyAnimationView, "alpha", 1f, 0f, 1f);
                // ofFloat()作用有两个
                // 1. 创建动画实例
                // 2. 参数设置：参数说明如下
                // Object object：需要操作的对象
                // String property：需要操作的对象的属性
                // float ....values：动画初始值 & 结束值（不固定长度）
                // 若是两个参数a,b，则动画效果则是从属性的a值到b值
                // 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
                // 以此类推
                // 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解
                // 设置动画运行的时长
//                animator.setDuration(500);
                // 设置动画延迟播放时间
//                animator.setStartDelay(500);
                // 设置动画重复播放次数 = 重放次数+1
                // 动画播放次数 = infinite时,动画无限重复
//                animator.setRepeatCount(0);
                // 设置重复播放动画模式
                // ValueAnimator.RESTART(默认):正序重放
                // ValueAnimator.REVERSE:倒序回放
//                animator.setRepeatMode(ValueAnimator.RESTART);
//                animator.start();

                //===========Java实现================
//                Animator objectAnimator = AnimatorInflater.loadAnimator(context, R.animator.object_animator);
//                objectAnimator.setTarget(propertyAnimationView);
//                objectAnimator.start();

                //===========透明度================
//                ObjectAnimator animator = ObjectAnimator.ofFloat(propertyAnimationView, "alpha", 1f, 0f, 1f);
//                // 表示的是:
//                // 动画作用对象是mButton
//                // 动画作用的对象的属性是透明度alpha
//                // 动画效果是:常规 - 全透明 - 常规
//                animator.setDuration(5000);
//                animator.start();

                //===========旋转================
//                ObjectAnimator animator = ObjectAnimator.ofFloat(propertyAnimationView, "rotation", 0f, 360f);
//                // 表示的是:
//                // 动画作用对象是mButton
//                // 动画作用的对象的属性是旋转alpha
//                // 动画效果是:0 - 360
//                animator.setDuration(5000);
//                animator.start();


                //===========平移================
//                float curTranslationX = propertyAnimationView.getTranslationX();
//                // 获得当前按钮的位置
//                ObjectAnimator animator = ObjectAnimator.ofFloat(propertyAnimationView, "translationX", curTranslationX, 300,curTranslationX);
//                // 表示的是:
//                // 动画作用对象是mButton
//                // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
//                // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
//                animator.setDuration(5000);
//                animator.start();


                //===========缩放================
//                ObjectAnimator animator = ObjectAnimator.ofFloat(propertyAnimationView, "scaleX", 1f, 3f, 1f);
//                // 表示的是:
//                // 动画作用对象是mButton
//                // 动画作用的对象的属性是X轴缩放
//                // 动画效果是:放大到3倍,再缩小到初始大小
//                animator.setDuration(5000);
//                animator.start();


//=========================AnimatorSet 类==================================================================
                /*
                * 组合动画（AnimatorSet 类）
                * 单一动画实现的效果相当有限，更多的使用场景是同时使用多种动画效果，即组合动画
                * 实现 组合动画 的功能：AnimatorSet类
                * 具体使用：
                * AnimatorSet.play(Animator anim)   ：播放当前动画
                * AnimatorSet.after(long delay)     ：将现有动画延迟x毫秒后执行
                * AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
                * AnimatorSet.after(Animator anim)  ：将现有动画插入到传入的动画之后执行
                * AnimatorSet.before(Animator anim) ：将现有动画插入到传入的动画之前执行
                * */

                // 步骤1：设置需要组合的动画效果
                // 平移动画
//                ObjectAnimator translation = ObjectAnimator.ofFloat(propertyAnimationView, "translationX", curTranslationX, 300, curTranslationX);
                // 旋转动画
//                ObjectAnimator rotate = ObjectAnimator.ofFloat(propertyAnimationView, "rotation", 0f, 360f);
                // 透明度动画
//                ObjectAnimator alpha = ObjectAnimator.ofFloat(propertyAnimationView, "alpha", 1f, 0f, 1f);
                // 步骤2：创建组合动画的对象
//                AnimatorSet animSet = new AnimatorSet();
                // 步骤3：根据需求组合动画
//                animSet.play(translation).with(rotate).before(alpha);
//                animSet.setDuration(5000);
//                animSet.start();


//                AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.set_animator);
                // 创建组合动画对象  &  加载XML动画
//                animator.setTarget(propertyAnimationView);
                // 设置动画作用对象
//                animator.start();
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

    // 关注1：ofInt（）源码分析
    public static ValueAnimator ofInt(int... values) {
        // 允许传入一个或多个Int参数
        // 1. 输入一个的情况（如a）：从0过渡到a；
        // 2. 输入多个的情况（如a，b，c）：先从a平滑过渡到b，再从b平滑过渡到C

        ValueAnimator anim = new ValueAnimator();
        // 创建动画对象
        anim.setIntValues(values);
        // 将传入的值赋值给动画对象
        return anim;
    }
}
