package video.ysh.com.supervideo.activity;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import video.ysh.com.supervideo.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected <T extends View> T bindViewId(@IdRes int resId) {
        return (T) findViewById(resId);
    }


    protected void setToolbar() {
        mToolbar = bindViewId(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    protected void setToolbarIcon(int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
        }
    }

    protected void setTitle(String title) {
        if (mToolbar != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    protected void setArrowToolbar(boolean isboolean) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(isboolean);
    }
}
