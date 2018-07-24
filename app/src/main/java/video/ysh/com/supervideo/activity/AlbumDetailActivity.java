package video.ysh.com.supervideo.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import video.ysh.com.supervideo.AppManager;
import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.api.OnGetAlbumDetailListener;
import video.ysh.com.supervideo.api.OnGetVideoPlayUrlListener;
import video.ysh.com.supervideo.api.SiteApi;
import video.ysh.com.supervideo.bean.Album;
import video.ysh.com.supervideo.bean.ErrorInfo;
import video.ysh.com.supervideo.bean.sohu.Video;
import video.ysh.com.supervideo.fragment.AlbumPlayGridFragment;
import video.ysh.com.supervideo.utils.ImageUtils;

public class AlbumDetailActivity extends BaseActivity implements View.OnClickListener {


    private Album mAlbum;
    private int mVideoNo;
    private boolean mIsShow;
    private ImageView mAlbumIV;
    private TextView mAlbumName;
    private TextView mAlbumDirector;
    private TextView mAlbumMainctor;
    private TextView mAlbumdesc;
    private RelativeLayout mRelativeLayout;

    private Button mSuperBitstreamButton;
    private Button mNormalBitstreamButton;
    private Button mHighBitstreamButton;
    private int mCurrentVideoPosition;

    private boolean isFavor;
    private AlbumPlayGridFragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mAlbum = intent.getParcelableExtra("album");
        mVideoNo = intent.getIntExtra("videoNo", 0);
        mIsShow = intent.getBooleanExtra("isShow", false);
        setToolbar();
        setArrowToolbar(true);
        setTitle(mAlbum.getTitle());

        mAlbumIV = bindViewId(R.id.iv_album_image);
        mAlbumName = bindViewId(R.id.tv_album_name); //name
        mAlbumDirector = bindViewId(R.id.tv_album_director); //导演
        mAlbumMainctor = bindViewId(R.id.tv_album_mainactor); //主演
        mAlbumdesc = bindViewId(R.id.tv_album_desc); //描述
        mRelativeLayout = bindViewId(R.id.album_desc_container);
        mSuperBitstreamButton = bindViewId(R.id.bt_super);
        mSuperBitstreamButton.setOnClickListener(this);
        mNormalBitstreamButton = bindViewId(R.id.bt_normal);
        mNormalBitstreamButton.setOnClickListener(this);
        mHighBitstreamButton = bindViewId(R.id.bt_high);
        mHighBitstreamButton.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        updateInfo();
        SiteApi.onGetAlbumDetail(mAlbum, new OnGetAlbumDetailListener() {

            @Override
            public void onGetAlbumDetailSuccess(final Album album) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAlbum=album;
                        updateInfo();
                        Log.e("mishow", "run: " + mIsShow);
                        mFragment = AlbumPlayGridFragment.newInstance(album, mIsShow, 0);
                        mFragment.setOnPlayVedioListener(mOnPlayVedioListener);
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFragment)
                                .commit();
                        getSupportFragmentManager().executePendingTransactions();
                    }
                });
            }

            @Override
            public void onGetAlbumDetailFailed(ErrorInfo info) {

            }
        });
    }

    private void updateInfo() {
        mAlbumName.setText(mAlbum.getTitle());
        //导演
        if (!TextUtils.isEmpty(mAlbum.getDirector())) {
            mAlbumDirector.setVisibility(View.VISIBLE);
            mAlbumDirector.setText(getResources().getText(R.string.director) + mAlbum.getDirector());
        } else {
            mAlbumDirector.setVisibility(View.GONE);
        }
        //主演
        if (!TextUtils.isEmpty(mAlbum.getMainActor())) {
            mAlbumMainctor.setVisibility(View.VISIBLE);
            mAlbumMainctor.setText(getResources().getText(R.string.mainactor) + mAlbum.getMainActor());
        } else {
            mAlbumMainctor.setVisibility(View.GONE);
        }
        //描述
        if (!TextUtils.isEmpty(mAlbum.getAlbumDesc())) {
            mAlbumdesc.setText(mAlbum.getAlbumDesc());
            mAlbumdesc.setVisibility(View.VISIBLE);
        } else {
            mAlbumdesc.setVisibility(View.GONE);
        }
        //海报图
        if (!TextUtils.isEmpty(mAlbum.getVerImgUrl())) {
            ImageUtils.disPlayImage(mAlbumIV, mAlbum.getVerImgUrl());

        } else if (!TextUtils.isEmpty(mAlbum.getHorImgUrl())) {
            ImageUtils.disPlayImage(mAlbumIV, mAlbum.getHorImgUrl());
        }
    }

    public static void launch(Activity activity, Album album, int videoNo, boolean isShow) {
        Intent intent = new Intent(activity, AlbumDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("album", album);
        intent.putExtra("video", videoNo);
        intent.putExtra("isShow", isShow);
        activity.startActivity(intent);
    }

    public static void launch(FragmentActivity activity, Album album) {
        Intent intent = new Intent(activity, AlbumDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("album", album);
        activity.startActivity(intent);
    }


    /**
     * 菜单点击时调用
     * 菜单项被点击时调用，也就是菜单项的监听方法。
     * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。 TODO Auto-generated
     * method stub
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_favor_item:
                if (isFavor) {
                    isFavor = false;
                    invalidateOptionsMenu();
                    Toast.makeText(getApplicationContext(), "已取消收藏", Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.action_unfavor_item:
                if (!isFavor) {
                    isFavor = true;
                    invalidateOptionsMenu();
                    Toast.makeText(getApplicationContext(), "已添加收藏", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * /**
     * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
     * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
     * if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.album_detail_menu, menu);
        return true;

    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等） TODO
     * Auto-generated method stub
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem favor = menu.findItem(R.id.action_favor_item);
        MenuItem unfavor = menu.findItem(R.id.action_unfavor_item);
        favor.setVisible(isFavor);
        unfavor.setVisible(!isFavor);
        return true;
    }


    private AlbumPlayGridFragment.OnPlayVedioListener mOnPlayVedioListener = new AlbumPlayGridFragment.OnPlayVedioListener() {
        @Override
        public void onSelect(Video video, int position) {
            //TODO 取视频的Url
            Log.e("ablumDeail", "onSelect: " + position);
            mCurrentVideoPosition = position;
            SiteApi.onGetVideoPlayUrl(video, mVideoUrlListener);
        }
    };

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String url = (String) button.getTag(R.id.key_video_url);
        int type = (int) button.getTag(R.id.key_video_stream);//码流类型
        Video video = (Video) button.getTag(R.id.key_video);
        int currentPosition = (int) button.getTag(R.id.key_current_video_number);
        if (AppManager.isNetWorkAvailable()) {
           // if (AppManager.isNetworkWifiAvailable()) {
                //TODO 放到历史中
                //mHistoryDBHelper.add(mAlbum);
                Intent intent = new Intent(AlbumDetailActivity.this, PlayActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("type", type);
                intent.putExtra("currentPosition", currentPosition);
                intent.putExtra("video", video);
                startActivity(intent);
//            } else {
//                // TODO 拓展
//            }
        }
    }

    public class StreamType {
        public static final int SUPER = 1;
        public static final int NORMAL = 2;
        public static final int HIGH = 3;
    }

    private OnGetVideoPlayUrlListener mVideoUrlListener = new OnGetVideoPlayUrlListener() {
        @Override
        public void onGetSuperUrl(final Video video, final String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("video", "run: " + video.toString());
                    mSuperBitstreamButton.setVisibility(View.VISIBLE);
                    mSuperBitstreamButton.setTag(R.id.key_video_url, url); //视频url
                    mSuperBitstreamButton.setTag(R.id.key_video, video);//视频info
                    mSuperBitstreamButton.setTag(R.id.key_current_video_number, mCurrentVideoPosition);//当前视频
                    mSuperBitstreamButton.setTag(R.id.key_video_stream, StreamType.SUPER); //码流
                }
            });
        }

        @Override
        public void onGetNoramlUrl(final Video video, final String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("video", "run: " + video.toString());

                    mNormalBitstreamButton.setVisibility(View.VISIBLE);
                    mNormalBitstreamButton.setTag(R.id.key_video_url, url); //视频url
                    mNormalBitstreamButton.setTag(R.id.key_video, video);//视频info
                    mNormalBitstreamButton.setTag(R.id.key_current_video_number, mCurrentVideoPosition);//当前视频
                    mNormalBitstreamButton.setTag(R.id.key_video_stream, StreamType.NORMAL); //码流
                }
            });
        }

        @Override
        public void onGetHighUrl(final Video video, final String url) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("video", "run: " + video.toString());

                    mHighBitstreamButton.setVisibility(View.VISIBLE);
                    mHighBitstreamButton.setTag(R.id.key_video_url, url); //视频url
                    mHighBitstreamButton.setTag(R.id.key_video, video);//视频info
                    mHighBitstreamButton.setTag(R.id.key_current_video_number, mCurrentVideoPosition);//当前视频
                    mHighBitstreamButton.setTag(R.id.key_video_stream, StreamType.HIGH); //码流
                }
            });
        }

        @Override
        public void onGetFailed(final ErrorInfo info) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("vedio", "run: " + info.toString());
                    hideAllButton();//请求播放源失败,不展示s
                }
            });
        }
    };

    private void hideAllButton() {
        mSuperBitstreamButton.setVisibility(View.GONE);
        mNormalBitstreamButton.setVisibility(View.GONE);
        mHighBitstreamButton.setVisibility(View.GONE);
    }

}