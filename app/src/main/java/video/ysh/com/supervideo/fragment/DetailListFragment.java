package video.ysh.com.supervideo.fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.activity.AlbumDetailActivity;
import video.ysh.com.supervideo.api.OnGetChannelAlbumListener;
import video.ysh.com.supervideo.api.SiteApi;
import video.ysh.com.supervideo.bean.Album;
import video.ysh.com.supervideo.bean.AlbumList;
import video.ysh.com.supervideo.bean.Channel;
import video.ysh.com.supervideo.bean.ErrorInfo;
import video.ysh.com.supervideo.bean.Site;
import video.ysh.com.supervideo.bean.sohu.Data;
import video.ysh.com.supervideo.utils.ImageUtils;
import video.ysh.com.supervideo.widget.PullLoadRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListFragment extends BaseFragment {

    private int mSideId;
    private int mChannId;

    private PullLoadRecyclerView mPullLoadRecyclerView;
    private TextView mEmptyView;

    private DataAdapter mDataAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private int pageSize = 30;
    private int pageNo = 1;

    private int mColumns;

    public static DetailListFragment newInstance(int sideId, int channId) {
        Bundle args = new Bundle();
        DetailListFragment fragment = new DetailListFragment();
        args.putInt("sideId", sideId);
        args.putInt("channId", channId);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mSideId = arguments.getInt("sideId");
        mChannId = arguments.getInt("channId");
        mDataAdapter = new DataAdapter(getContext(), new Channel(mChannId + 1, getContext()));
        if (mSideId == Site.LETV) { //2 列
            mDataAdapter.setColumns(2);
            mColumns = 2;
        } else {
            mDataAdapter.setColumns(3);
            mColumns = 3;
        }
    }

    @Override
    protected void initView() {
        mEmptyView = bindViewId(R.id.tv_empty);
        mEmptyView.setText(R.string.load_more_text);
        mPullLoadRecyclerView = bindViewId(R.id.pullLoadrecyclerview);
        mPullLoadRecyclerView.setGlidLayout(mColumns);
        mPullLoadRecyclerView.setAdapter(mDataAdapter);
        mPullLoadRecyclerView.setOnPullLoadMoreListener(new PullLoadRecyclerView.onPullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        mPullLoadRecyclerView.setRefreshCompleted();
                    }
                }, 1500);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreData();
                        mPullLoadRecyclerView.setLoadMoreCompleted();
                    }
                }, 1500);
            }
        });
    }

    private void refreshData() {
        //TODO 请求接口 刷新数据
        pageNo = 0;
        loadData(1);
    }

    private void loadMoreData() {
        //TODO 请求接口 加载数据
        pageNo++;
        loadData(0);
    }


    @Override
    protected void initData() {
        loadData(1);
    }

    private void loadData(final int type) {
        final Channel channel = new Channel(mChannId + 1, getContext());
        SiteApi.onGetChannelAlums(mSideId, channel, pageNo, pageSize, new OnGetChannelAlbumListener() {
            @Override
            public void onSuccess(final AlbumList albumList) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mEmptyView.setVisibility(View.GONE);
                        mDataAdapter.setData(albumList, type);
                    }
                });
            }

            @Override
            public void onFaild(ErrorInfo errorInfo) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mEmptyView.setText(R.string.data_failed_tip);
                        mEmptyView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_list;
    }

    class DataAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private Context mContext;
        private Channel mChannel;
        private AlbumList mAlbums = new AlbumList();
        private int mColumns;

        public DataAdapter(Context context, Channel channel) {
            mContext = context;
            mChannel = channel;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.detailist_item, null);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            view.setTag(itemViewHolder);
            return itemViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
            if (mAlbums.size() == 0) {
                return;
            }
            final Album album = mAlbums.get(position);
            itemViewHolder.albumName.setText(album.getTitle());
            if (album.getTip().isEmpty()) {
                itemViewHolder.albumTip.setVisibility(View.GONE);
            } else {
                itemViewHolder.albumTip.setText(album.getTip());
            }
            Point point = null;
            //重新计算宽高
            if (mColumns == 2) {
                point = ImageUtils.getHorPostSize(mContext, mColumns);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(point.x, point.y);
                itemViewHolder.albumPoster.setLayoutParams(params);
            } else {
                point = ImageUtils.getVerPostSize(mContext, mColumns);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(point.x, point.y);
                itemViewHolder.albumPoster.setLayoutParams(params);
            }

            if (album.getVerImgUrl() != null) {
                ImageUtils.disPlayImage(itemViewHolder.albumPoster, album.getVerImgUrl(), point.x, point.y);
            } else if (album.getHorImgUrl() != null) {
                ImageUtils.disPlayImage(itemViewHolder.albumPoster, album.getHorImgUrl(), point.x, point.y);
            } else {
                //TOD 默认图
            }
            itemViewHolder.resultContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mChannId == Channel.DOCUMENTRY || mChannId == Channel.MOVIE
                            || mChannId == Channel.MUSIC || mChannId == Channel.VARIETY) {
                        //TODO 跳转到详情页
                        AlbumDetailActivity.launch(getActivity(),album,0,true);
                    }else {
                        AlbumDetailActivity.launch(getActivity(),album);

                    }
                }
            });

        }


        @Override
        public int getItemCount() {
            return mAlbums.size();
        }

        public void setData(AlbumList albumList, int type) {
            if (type == 1) {
                mAlbums.clear();
                mAlbums.addAll(albumList);
            } else {
                mAlbums.addAll(albumList);
            }

            notifyDataSetChanged();
        }

        //显示列数
        public void setColumns(int columns) {
            mColumns = columns;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout resultContainer;
        private ImageView albumPoster;
        private TextView albumName;
        private TextView albumTip;

        public ItemViewHolder(View view) {
            super(view);
            resultContainer = (LinearLayout) view.findViewById(R.id.album_container);
            albumPoster = (ImageView) view.findViewById(R.id.iv_album_poster);
            albumTip = (TextView) view.findViewById(R.id.tv_album_tip);
            albumName = (TextView) view.findViewById(R.id.tv_album_name);
        }
    }

}
