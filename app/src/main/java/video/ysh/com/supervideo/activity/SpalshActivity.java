package video.ysh.com.supervideo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import video.ysh.com.supervideo.R;
import video.ysh.com.supervideo.activity.GuideActivity;
import video.ysh.com.supervideo.activity.HomeActivity;

/**
 * @author yanshihao
 */
public class SpalshActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startHomeActivity();
                    break;
                case 0:
                    startGuideActivity();
                    break;
                default:
                    break;
            }
        }
    };

    private void startGuideActivity() {
        Intent intent=new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void startHomeActivity() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        init();
    }

    private void init() {
        boolean mIsFrist = mSharedPreferences.getBoolean("mIsFrist", true);
        if (mIsFrist) {
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            mHandler.sendEmptyMessageDelayed(1, 2000);
        }
    }
}
