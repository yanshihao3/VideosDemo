package video.ysh.com.supervideo.utils;


import okhttp3.Callback;
import okhttp3.Request;
import video.ysh.com.supervideo.AppManager;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

public class OkHttpUtils {

    private static final String REQUEST_TAG = "okhttp";

    private static Request buildRuquest(String url) {
        if (AppManager.isNetWorkAvailable()) {
            Request request = new Request.Builder()
                    .tag(REQUEST_TAG)
                    .url(url)
                    .build();
            return request;
        }
        return null;
    }

    public static void excute(String url, Callback callback) {
        Request request = buildRuquest(url);
        excute(request,callback);
    }

    public static void excute(Request request, Callback callback) {
        AppManager.getOkhttpClinet().newCall(request).enqueue(callback);
    }
}
