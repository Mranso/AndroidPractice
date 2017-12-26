package com.anso.androidpractice.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.anso.androidpractice.R;

public class ViewPagerBannerActivity extends AppCompatActivity {

    private Context context;
    private ViewPager viewPager;
    private int[] images = {R.drawable.view_pager_banner_a, R.drawable.view_pager_banner_b,
            R.drawable.view_pager_banner_c, R.drawable.view_pager_banner_d, R.drawable.view_pager_banner_e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_banner);
        context = this;
        initView();
        initData();
    }

    private void initData() {
        //设置Page间间距
        viewPager.setPageMargin(40);
        //设置缓存的页面数量
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new ViewPagerAdapter());
        //决定动画效果
        viewPager.setPageTransformer(true, new ScaleInTransformer());
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager_banner);
    }

    private class ViewPagerAdapter extends PagerAdapter {

        //获取当前界面数量
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        //判断是否由对象生成界面，官方建议直接返回 return view == object
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //如果页面不是当前显示的页面也不是要缓存的页面，会调用这个方法，将页面销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void startUpdate(ViewGroup container) {
            super.startUpdate(container);
            ViewPager viewPager = (ViewPager) container;
            int position = viewPager.getCurrentItem();
            if (position == 0) {
                position = getFirstItemPosition();
            } else if (position == getCount() - 1) {
                position = getLastItemPosition();
            }
            viewPager.setCurrentItem(position, false);

        }

        //要显示的页面或需要缓存的页面，会调用这个方法进行布局的初始化
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(context).inflate(R.layout.view_pager_banner_item_layout, container, false);
            ImageView imageView = (ImageView)view.findViewById(R.id.banner_item_image);
            final int realPosition = getRealPosition(position);
            imageView.setImageResource(images[realPosition]);
            container.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ViewPagerBannerActivity.this, "click position= " + realPosition, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        private int getRealCount() {
            return images.length;
        }

        private int getRealPosition(int position) {
            return position % getRealCount();
        }

        private int getFirstItemPosition() {
            return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount();
        }

        private int getLastItemPosition() {
            return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount() - 1;
        }
    }
}
