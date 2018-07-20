package video.ysh.com.supervideo.api;


import video.ysh.com.supervideo.bean.Album;
import video.ysh.com.supervideo.bean.ErrorInfo;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

public interface OnGetAlbumDetailListener {
    void onGetAlbumDetailSuccess(Album album);
    void onGetAlbumDetailFailed(ErrorInfo info);
}
