package video.ysh.com.supervideo.api;


import video.ysh.com.supervideo.bean.ErrorInfo;
import video.ysh.com.supervideo.bean.sohu.Video;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

public interface OnGetVideoPlayUrlListener {

    void onGetSuperUrl(Video video, String url);//超清url

    void onGetNoramlUrl(Video video, String url);//标清url

    void onGetHighUrl(Video video, String url);//高清url

    void onGetFailed(ErrorInfo info);

}
