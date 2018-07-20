package video.ysh.com.supervideo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import video.ysh.com.supervideo.R;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午5:38
 * - @Email whynightcode@gmail.com
 */
public class PullLoadRecyclerView extends LinearLayout {

    private Context mContext;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private View mFootView;

    private boolean mIsRefresh = false; //是否是刷新
    private boolean mIsLoadMore = false; //是否是加载更多

    private AnimationDrawable mAnimationDrawable;

    private onPullLoadMoreListener mOnPullLoadMoreListener;

    public PullLoadRecyclerView(Context context) {
        this(context, null);
    }

    public PullLoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullLoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pull_loadmore_layout, null);
        mSwipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mIsRefresh) {
                    mIsRefresh = true;
                    refreshData();
                }
            }
        });

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true); //设置固定大小
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//使用默认动画
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mIsRefresh || mIsLoadMore;
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerViewOnSroll());
        mRecyclerView.setHorizontalScrollBarEnabled(false); //隐藏滚动条

        mFootView = view.findViewById(R.id.foot_view);
        ImageView imageView = mFootView.findViewById(R.id.iv_load_img);
        imageView.setBackgroundResource(R.drawable.imooc_loading);
        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        TextView textView = mFootView.findViewById(R.id.tv_load_text);
        mFootView.setVisibility(GONE);
        this.addView(view);
    }

    //设置刷新完毕
    public void setRefreshCompleted() {
        mIsRefresh = false;
        setRefreshing(false);
    }

    //设置是否正在刷新
    private void setRefreshing(final boolean isRefreshing) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });
    }

    public void setLoadMoreCompleted() {
        mIsLoadMore = false;
        mIsRefresh = false;
        setRefreshing(false);
        mFootView.animate().translationY(mFootView.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300).start();
    }

    //外部设置GridLayoutManager 的列数
    public void setGlidLayout(int spanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void refreshData() {
        if (mOnPullLoadMoreListener != null) {
            mOnPullLoadMoreListener.onRefresh();
        }
    }

    private void loadMoreData() {
        if (mOnPullLoadMoreListener != null) {
            mOnPullLoadMoreListener.onLoadMore();
            mFootView.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            mFootView.setVisibility(VISIBLE);
                            mAnimationDrawable.start();
                        }
                    }).start();
            invalidate();
            mOnPullLoadMoreListener.onLoadMore();

        }
    }


    public void setOnPullLoadMoreListener(onPullLoadMoreListener onPullLoadMoreListener) {
        mOnPullLoadMoreListener = onPullLoadMoreListener;
    }

    public interface onPullLoadMoreListener {
        void onRefresh();

        void onLoadMore();
    }

    class RecyclerViewOnSroll extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastItem = 0;
            int fristItem = 0;

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int count = layoutManager.getItemCount();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                //第一个完全可见的itme
                fristItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                //最后一个完全可见的itme
                lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                if (fristItem == 0 || fristItem == RecyclerView.NO_POSITION) {
                    lastItem = gridLayoutManager.findLastVisibleItemPosition();
                }
            }
            //什么时候触发上拉加载更多
            //1。加载更多
            //2。count ==lastitem
            //3。mSwipeRefreshLayout 可用
            //4。不是处于下拉刷新状态
            //5。偏移量大于0

            if (!mIsLoadMore
                    && count-1 == lastItem
                    && mSwipeRefreshLayout.isEnabled()
                    && !mIsRefresh
                    && (dx > 0 || dy > 0)) {
                mIsLoadMore = true;
                loadMoreData();//在加载更多时,禁止mSwipeRefreshLayout使用
                mSwipeRefreshLayout.setEnabled(false);
            } else {
                mSwipeRefreshLayout.setEnabled(true);
            }

        }
    }


}
