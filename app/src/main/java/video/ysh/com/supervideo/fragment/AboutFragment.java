package video.ysh.com.supervideo.fragment;


import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import video.ysh.com.supervideo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {


    private TextView mTextView;

    @Override
    protected void initView() {
        mTextView=bindViewId(R.id.app_about);
        mTextView.setAutoLinkMask(Linkify.ALL); //表示文字中有链接可点击
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());//表示文字可以滚动
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
