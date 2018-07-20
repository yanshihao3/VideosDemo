package video.ysh.com.supervideo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.bean.Site;
import video.ysh.com.supervideo.fragment.DetailListFragment;
import video.ysh.com.supervideo.indircator.CoolIndicatorLayout;
import video.ysh.com.supervideo.indircator.IPagerIndicatorView;
import video.ysh.com.supervideo.indircator.IPagerTitle;
import video.ysh.com.supervideo.indircator.ViewPagerIndicatorAdapter;
import video.ysh.com.supervideo.indircator.ViewPagerIndicatorLayout;
import video.ysh.com.supervideo.indircator.ViewPagerIndicatorView;
import video.ysh.com.supervideo.indircator.ViewPagerTextView;
import video.ysh.com.supervideo.indircator.ViewPagerWrapper;

public class DetailListActivity extends BaseActivity {
    private int[] mTitle = new int[]{R.string.channel_series, R.string.channel_movie, R.string.channel_comic,
            R.string.channel_documentary, R.string.channel_music, R.string.channel_variety, R.string.channel_live, R.string.channel_favorite
            , R.string.channel_history
    };
    private String[] mName=new String[]{"搜狐视频","乐视视频"};

    private List<String >mDataSet= Arrays.asList(mName);

    private int mChannelId;

    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_list;

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            mChannelId = intent.getIntExtra("channel", 0);
        }
        setToolbar();
        setArrowToolbar(true);
        setTitle(mTitle[mChannelId]);
        CoolIndicatorLayout coolIndicatorLayout=bindViewId(R.id.coolindicatorlayout);
        ViewPagerIndicatorLayout viewPagerIndicatorLayout=new ViewPagerIndicatorLayout(this);
        coolIndicatorLayout.setPagerIndicatorLayout(viewPagerIndicatorLayout);
        viewPagerIndicatorLayout.setAdapter(new ViewPagerIndicatorAdapter() {
            @Override
            public int getCount() {
                return mDataSet.size();
            }

            @Override
            public IPagerTitle getTitle(Context context, final int index) {
                ViewPagerTextView textView =new ViewPagerTextView(context);
                textView.setText(mDataSet.get(index));
                textView.setNormalColor(Color.parseColor("#333333"));
                textView.setSelectedColor(Color.parseColor("#e94222"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return textView;
            }

            @Override
            public IPagerIndicatorView getIndicator(Context conext) {
                ViewPagerIndicatorView viewPagerIndicatorView=new ViewPagerIndicatorView(conext);
                viewPagerIndicatorView.setFillColor(Color.parseColor("#ebe4e3"));
                return viewPagerIndicatorView;
            }
        });
        mViewPager=bindViewId(R.id.pager);
        mViewPager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager()));
        ViewPagerWrapper.with(coolIndicatorLayout,mViewPager).compose();

    }

    //处理左上角箭头
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void initData() {

    }

    public static void launchDetailListActivity(Context context, int id) {
        Intent intent = new Intent(context, DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("channel", id);
        context.startActivity(intent);
    }

     class DetailPagerAdapter extends FragmentPagerAdapter{

        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

         @Override
        public Fragment getItem(int position) {
            Fragment fragment= DetailListFragment.newInstance(new Site(position+1).getSiteId() ,mChannelId);
            return fragment;
        }

        @Override
        public int getCount() {
            return Site.MAX_SITE;
        }
    }
}
