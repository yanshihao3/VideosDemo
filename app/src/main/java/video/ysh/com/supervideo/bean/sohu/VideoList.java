package video.ysh.com.supervideo.bean.sohu;

import android.util.Log;


import java.util.ArrayList;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */
public class VideoList extends ArrayList<Video> {
    private static final String TAG = VideoList.class.getSimpleName();

    public void debug () {
        for (Video a : this) {
            Log.d(TAG, ">> videList " + a.toString());
        }
    }
}
