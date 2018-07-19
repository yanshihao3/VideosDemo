package video.ysh.com.supervideo.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/17 下午5:22
 * - @Email whynightcode@gmail.com
 */
public abstract class BaseFragment extends Fragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(), container, false);
        initView();
        initData();
        return mContentView;
    }

    protected <T extends View> T bindViewId(@IdRes int resId) {
        return (T) mContentView.findViewById(resId);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();
}
