package video.ysh.com.supervideo.api;

import video.ysh.com.supervideo.bean.AlbumList;
import video.ysh.com.supervideo.bean.ErrorInfo;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 上午10:15
 * - @Email whynightcode@gmail.com
 */
public interface OnGetChannelAlbumListener {
    void onSuccess(AlbumList albumList);
    void onFaild(ErrorInfo errorInfo);
}
