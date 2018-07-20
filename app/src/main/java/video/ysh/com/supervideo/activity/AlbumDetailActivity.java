package video.ysh.com.supervideo.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.bean.Album;

public class AlbumDetailActivity extends BaseActivity {


    private Album mAlbum;
    private int mVideoNo;
    private boolean mIsShow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mAlbum = intent.getParcelableExtra("album");
        mVideoNo = intent.getIntExtra("videoNo", 0);
        mIsShow = intent.getBooleanExtra("isShow", true);
        setToolbar();
        setArrowToolbar(true);
        setTitle(mAlbum.getTitle());
    }

    @Override
    protected void initData() {

    }

    public static void launch(Activity activity, Album album, int videoNo, boolean isShow) {
        Intent intent = new Intent(activity, DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("album", album);
        intent.putExtra("video", videoNo);
        intent.putExtra("isShow", isShow);
        activity.startActivity(intent);
    }

    public static void launch(FragmentActivity activity, Album album) {
        Intent intent = new Intent(activity, DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("album", album);
        activity.startActivity(intent);
    }
}
