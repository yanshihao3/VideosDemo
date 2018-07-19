package video.ysh.com.supervideo.fragment;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import video.ysh.com.supervideo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends BaseFragment {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    private static final int MAX = 100;
    private static final String URL = "https://my.csdn.net/yanshihao3";

    @Override
    protected void initView() {
        mWebView = bindViewId(R.id.webview);
        mProgressBar = bindViewId(R.id.pb_progressbar);
        WebSettings webSettings = mWebView.getSettings(); //设置webview的属性
        webSettings.setBuiltInZoomControls(true); //支持放大缩小
        webSettings.setJavaScriptEnabled(true);//支持JavaScript
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.loadUrl(URL);

    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
            if (newProgress == MAX) {
                mProgressBar.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    };


    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }
}
