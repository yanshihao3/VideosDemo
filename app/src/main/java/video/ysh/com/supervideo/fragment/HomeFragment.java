package video.ysh.com.supervideo.fragment;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import video.ysh.com.supervideo.activity.DetailListActivity;
import video.ysh.com.supervideo.bean.Channel;
import video.ysh.com.supervideo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private int[] mDes = new int[]
            {R.string.a_name, R.string.b_name, R.string.c_name, R.string.d_name
                    , R.string.e_name
            };

    private int[] mImage = new int[]{R.mipmap.a, R.mipmap.b, R.mipmap.c,
            R.mipmap.d, R.mipmap.e
    };

    private int[] mTitle = new int[]{R.string.channel_series, R.string.channel_movie, R.string.channel_comic,
            R.string.channel_documentary, R.string.channel_music, R.string.channel_variety, R.string.channel_live, R.string.channel_favorite
            , R.string.channel_history
    };
    private int[] mChannel = new int[]{R.mipmap.ic_show, R.mipmap.ic_movie,
            R.mipmap.ic_comic, R.mipmap.ic_documentary, R.mipmap.ic_music, R.mipmap.ic_variety,
            R.mipmap.ic_live, R.mipmap.ic_bookmark, R.mipmap.ic_history

    };
    private LoopViewPager mLoopViewPager;
    private CircleIndicator mCircleIndicator;
    private GridView mGridView;

    private List<Channel> mChannels;

    @Override
    protected void initView() {
        mLoopViewPager = bindViewId(R.id.viewpager);
        mCircleIndicator = bindViewId(R.id.indicator);
        mLoopViewPager.setAdapter(new HomePagerAdapter());
        mLoopViewPager.setLooperPic(true);
        mCircleIndicator.setViewPager(mLoopViewPager);
        mGridView = bindViewId(R.id.gv_channnel);
        mChannels = new ArrayList<>();
        for (int i = 0; i < mChannel.length; i++) {
            mChannels.add(new Channel(i, mTitle[i], mChannel[i]));
        }
        mGridView.setAdapter(new ChannelAdapter());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 6:
                        //TODO  直播
                        Log.e(TAG, "onItemClick: "+"直播" );
                        break;
                    case 7:
                        //TODO 收藏
                        Log.e(TAG, "onItemClick: "+"收藏" );

                        break;

                    case 8:
                        //TODO 历史
                        Log.e(TAG, "onItemClick: "+"历史" );

                        break;
                    default:
                        //TODO 跳转到相应的频道界面
                        Log.e(TAG, "onItemClick: "+"频道" );
                        DetailListActivity.launchDetailListActivity(getContext(),position);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    class ChannelAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mChannel.length;
        }

        @Override
        public Object getItem(int position) {
            return mChannels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mChannels.get(position).getId();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_grid_item, parent, false);
                viewHolder.mTextView = convertView.findViewById(R.id.tv_home_item_text);
                viewHolder.mImageView = convertView.findViewById(R.id.iv_home_item_img);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(mTitle[position]);
            viewHolder.mImageView.setImageResource(mChannel[position]);
            return convertView;
        }

        class ViewHolder {
            TextView mTextView;
            ImageView mImageView;
        }
    }

    class HomePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.home_pic_item, null);
            TextView textView = view.findViewById(R.id.tv_dec);
            ImageView imageView = view.findViewById(R.id.iv_img);
            textView.setText(mDes[position]);
            imageView.setImageResource(mImage[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
