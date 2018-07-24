package video.ysh.com.supervideo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.api.OnGetVideoListener;
import video.ysh.com.supervideo.api.SiteApi;
import video.ysh.com.supervideo.bean.Album;
import video.ysh.com.supervideo.bean.ErrorInfo;
import video.ysh.com.supervideo.bean.sohu.Video;
import video.ysh.com.supervideo.bean.sohu.VideoList;
import video.ysh.com.supervideo.widget.CustomGridView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumPlayGridFragment extends BaseFragment {

    private Handler mHandler = new Handler();
    private Album mAlbum;
    private boolean mIsShowDes;
    private int mPosition;
    private boolean mIsFristShow = true;
    private int mPageSize;
    private int mPageNo;
    private TextView mEmpty_tv;
    private VideoItemAdapter mItemAdapter;

    private CustomGridView mGridView;
    private int mCurrPosition; //当前点击的位置

    public AlbumPlayGridFragment() {
        // Required empty public constructor
    }

    private OnPlayVedioListener mOnPlayVedioListener;

    public void setOnPlayVedioListener(OnPlayVedioListener onPlayVedioListener) {
        mOnPlayVedioListener = onPlayVedioListener;
    }

    public interface OnPlayVedioListener {
        void onSelect(Video video, int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mPosition = arguments.getInt("initVedioPosition", 0);
            mAlbum = arguments.getParcelable("ablum");
            mIsShowDes = arguments.getBoolean("isShowDes");
            mPageSize = 50;
            mCurrPosition = mPosition;
            mPageNo = 0;
        }
    }

    private void loadData() {
        mPageNo++;
        SiteApi.onGetVideo(mPageSize, mPageNo, mAlbum, new OnGetVideoListener() {
            @Override
            public void OnGetVideoSuccess(final VideoList videoList) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mEmpty_tv.setVisibility(View.GONE);
                        mItemAdapter.addVideos(videoList);
                        mItemAdapter.notifyDataSetChanged();
                        if (mItemAdapter.getCount() > mPosition && mIsFristShow) {
                            mIsFristShow = false;
                            mGridView.setSelection(mPosition);
                            mGridView.setItemChecked(mPosition, true);
                        }
                    }
                });
            }

            @Override
            public void OnGetVideoFailed(ErrorInfo info) {

            }
        });

    }

    public static AlbumPlayGridFragment newInstance(Album album, boolean isShowDes, int initVedioPosition) {
        Bundle args = new Bundle();
        AlbumPlayGridFragment fragment = new AlbumPlayGridFragment();
        args.putParcelable("ablum", album);
        args.putBoolean("isShowDes", isShowDes);
        args.putInt("initVedioPosition", initVedioPosition);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        mItemAdapter = new VideoItemAdapter(getContext(), mAlbum.getVideoTotal(), new OnVideoSelectedListener() {
            @Override
            public void onVideoSelected(Video video, int position) {
                Log.e("position", "onVideoSelected: " + position);
                if (mGridView != null) {
                    Log.e("position", "onVideoSelected: " + position);
                    mGridView.setSelection(position);
                    mGridView.setItemChecked(position, true);
                    mCurrPosition = position;
                    if (mOnPlayVedioListener != null) {
                        mOnPlayVedioListener.onSelect(video, position);
                    }
                }
            }

        });
        mEmpty_tv = bindViewId(R.id.tv_empty);
        mEmpty_tv.setVisibility(View.VISIBLE);
        mEmpty_tv.setText(R.string.no_date_text);
        mGridView = bindViewId(R.id.gridview);
        //mIsShowDesc 表示同样是剧集,综艺节目是xx期,电视剧集是数字, 1表示综艺,或纪录片类,6表示动漫,电视剧
        mGridView.setNumColumns(mIsShowDes ? 6 : 1);
        if (mIsShowDes) {
            mItemAdapter.setIsShowTitleContent(false);
        } else {
            mItemAdapter.setIsShowTitleContent(true);
        }
        mGridView.setAdapter(mItemAdapter);
        if (mAlbum.getVideoTotal() > 0 && mAlbum.getVideoTotal() > mPageSize) {
            mGridView.setHasMoreItem(true);
        } else {
            mGridView.setHasMoreItem(false);
        }
        mGridView.setOnLoadMoreListener(new CustomGridView.OnLoadMoreListener() {
            @Override
            public void onLoadMoreItems() {
                loadData();
            }
        });
    }

    @Override
    protected void initData() {
        loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album_play_grid;
    }

}
