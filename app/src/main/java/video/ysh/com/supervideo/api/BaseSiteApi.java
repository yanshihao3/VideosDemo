package video.ysh.com.supervideo.api;

import video.ysh.com.supervideo.bean.Channel;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */
public abstract class BaseSiteApi {
    abstract void onGetChannelAlbums(Channel channel, int pageNo, int pageSize, OnGetChannelAlbumListener onGetChannelAlbumListener);
}
