package video.ysh.com.supervideo.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import video.ysh.com.supervideo.R;

public class LiveActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    protected void initView() {
        mRecyclerView = bindViewId(R.id.lv_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new MyItemDecoration(this));
        LiveItemAdapter adapter = new LiveItemAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.scrollToPosition(0);//回到第一个位置
    }

    @Override
    protected void initData() {

    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, LiveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
