package video.ysh.com.supervideo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import video.ysh.com.supervideo.R;


public class GuideActivity extends AppCompatActivity {

    private List<View> mViews;
    private ImageView[] mImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guild);
        initView();
        initViewPager();
        initDots();
    }

    private void initDots() {
        LinearLayout linearLayout = findViewById(R.id.ll_dots);
        mImageViews = new ImageView[mViews.size()];
        for (int i = 0; i < mViews.size(); i++) {
            mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
            mImageViews[i].setEnabled(false);
        }
        mImageViews[0].setEnabled(true);
    }

    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyViewPagerAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViews = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        mViews.add(inflater.inflate(R.layout.guide_one_layout, null));
        mViews.add(inflater.inflate(R.layout.guide_two_layout, null));
        mViews.add(inflater.inflate(R.layout.guide_three_layout, null));

    }

    private void setGuide() {
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        config.edit().putBoolean("mIsFrist",false).commit();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void setCurrentDot(int position) {
        for (int i = 0; i < mImageViews.length; i++) {
            mImageViews[i].setEnabled(false);
            if (i == position) {
                mImageViews[i].setEnabled(true);
            } else {
            }
        }
    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mViews != null) {
                return mViews.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViews.get(position));
            if (position==mViews.size()-1){
                mViews.get(position).findViewById(R.id.iv_start).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startHomeActivity();
                        setGuide();
                    }
                });
            }
            return mViews.get(position);
        }
    }
}
