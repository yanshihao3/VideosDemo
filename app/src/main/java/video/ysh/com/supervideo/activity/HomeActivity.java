package video.ysh.com.supervideo.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toolbar;

import video.ysh.com.supervideo.FragmentMagerWarper;
import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.fragment.AboutFragment;
import video.ysh.com.supervideo.fragment.BaseFragment;
import video.ysh.com.supervideo.fragment.BlogFragment;
import video.ysh.com.supervideo.fragment.HomeFragment;

public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private MenuItem mMenuItem;
    private FragmentManager mManager;
    private BaseFragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        setToolbar();
        setToolbarIcon(R.mipmap.ic_drawer_home);
        setTitle("视频");
        mDrawerLayout = bindViewId(R.id.drawerlayout);
        mNavigationView = bindViewId(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        mMenuItem = mNavigationView.getMenu().getItem(0);
        mMenuItem.setChecked(true);

        initFragment();
        handleNevigationview();
    }

    private void initFragment() {
        mFragment = FragmentMagerWarper.newInstance().createFragment(HomeFragment.class, false);
        mManager = getSupportFragmentManager();
        if (mFragment==null){
            Log.e("home", "initFragment: ");
        }
        mManager.beginTransaction().add(R.id.framelayout,mFragment).commit();
    }

    private void handleNevigationview() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.navigation_item_about:
                        //TODO
                        switchFragment(AboutFragment.class);
                        setTitle("关于");
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(BlogFragment.class);
                        setTitle("我的博客");
                        //TODO
                        break;
                    case R.id.navigation_item_video:
                        switchFragment(HomeFragment.class);
                        setTitle("视频");
                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mMenuItem = item;
                mMenuItem.setChecked(true);
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        BaseFragment fragment = FragmentMagerWarper.newInstance().createFragment(clazz, false);
        if (fragment.isAdded()){
            mManager.beginTransaction().hide(mFragment).show(fragment).commitAllowingStateLoss();
        }else {
            mManager.beginTransaction().hide(mFragment).add(R.id.framelayout,fragment).commitAllowingStateLoss();
        }
        mFragment= fragment;
    }

    @Override
    protected void initData() {

    }
}
