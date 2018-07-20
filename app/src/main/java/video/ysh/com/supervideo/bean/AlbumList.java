package video.ysh.com.supervideo.bean;

import android.util.Log;

import java.util.ArrayList;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

public class AlbumList extends ArrayList<Album>{

    private static final String TAG = AlbumList.class.getSimpleName();

    public void debug () {
        for (Album a : this) {
            Log.d(TAG, ">> albumlist " + a.toString());
        }
    }

}
