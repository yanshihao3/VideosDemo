package video.ysh.com.supervideo.api;


import video.ysh.com.supervideo.bean.ErrorInfo;
import video.ysh.com.supervideo.bean.sohu.VideoList;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

public interface OnGetVideoListener {
    void OnGetVideoSuccess(VideoList videoList);
    void OnGetVideoFailed(ErrorInfo info);
}
