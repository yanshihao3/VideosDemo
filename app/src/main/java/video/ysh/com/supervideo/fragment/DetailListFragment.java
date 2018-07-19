package video.ysh.com.supervideo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.bean.Site;
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
    private Handler mHandler=new Handler(Looper.getMainLooper());

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
        if (mSideId== Site.LETV){ //2 列
            mDataAdapter=new DataAdapter();
        }
    }

    @Override
    protected void initView() {
        mEmptyView=bindViewId(R.id.tv_empty);
        mEmptyView.setText(R.string.data_failed_tip);
        mPullLoadRecyclerView = bindViewId(R.id.pullLoadrecyclerview);
        mPullLoadRecyclerView.setGlidLayout(3);
        mPullLoadRecyclerView.setAdapter(new DataAdapter());
        mPullLoadRecyclerView.setOnPullLoadMoreListener(new PullLoadRecyclerView.onPullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        mPullLoadRecyclerView.setRefreshCompleted();
                    }
                },1500);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreData();
                        mPullLoadRecyclerView.setLoadMoreCompleted();
                    }
                },1500);
            }
        });
    }

    private void refreshData() {
        //TODO 请求接口 刷新数据
    }

    private void loadMoreData() {
        //TODO 请求接口 加载数据
    }

    @Override
    protected void initData() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_list;
    }

    class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
