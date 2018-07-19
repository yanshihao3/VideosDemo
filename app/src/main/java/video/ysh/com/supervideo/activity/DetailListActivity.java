package video.ysh.com.supervideo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.bean.Site;
import video.ysh.com.supervideo.fragment.DetailListFragment;

public class DetailListActivity extends BaseActivity {
    private int[] mTitle = new int[]{R.string.channel_series, R.string.channel_movie, R.string.channel_comic,
            R.string.channel_documentary, R.string.channel_music, R.string.channel_variety, R.string.channel_live, R.string.channel_favorite
            , R.string.channel_history
    };

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
        mViewPager=bindViewId(R.id.pager);
        mViewPager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager()));
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
