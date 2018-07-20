package video.ysh.com.supervideo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 上午10:47
 * - @Email whynightcode@gmail.com
 */
public class AppManager extends Application {
    private static Gson GSON;
    private static OkHttpClient OKHTTPCLIENT;
    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        GSON = new Gson();
        OKHTTPCLIENT = new OkHttpClient();
        CONTEXT = this;
    }

    public static Context getContext() {
        return CONTEXT;
    }

    public static OkHttpClient getOkhttpClinet() {
        return OKHTTPCLIENT;
    }

    public static Gson getGson() {
        return GSON;
    }

    //当前网络是否可用
    public static boolean isNetWorkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) CONTEXT.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }
}
