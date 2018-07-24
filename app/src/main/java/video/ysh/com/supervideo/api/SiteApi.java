package video.ysh.com.supervideo.api;

import video.ysh.com.supervideo.bean.Album;
import video.ysh.com.supervideo.bean.Channel;
import video.ysh.com.supervideo.bean.Site;
import video.ysh.com.supervideo.bean.sohu.Video;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 上午10:12
 * - @Email whynightcode@gmail.com
 */
public class SiteApi {

    public static void onGetChannelAlums(int siteId, Channel channelId, int pageNo, int pageSize, OnGetChannelAlbumListener listener) {
        switch (siteId) {
            case Site.LETV:
                new LetvApi().onGetChannelAlbums(channelId, pageNo, pageSize, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlbums(channelId, pageNo, pageSize, listener);
                break;
        }
    }

    public static void onGetAlbumDetail(Album album, OnGetAlbumDetailListener listener) {
        int siteId = album.getSite().getSiteId();
        switch (siteId) {
            case Site.LETV:
                new LetvApi().onGetAlbumDetail(album, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetAlbumDetail(album, listener);
                break;
        }
    }

    /**
     * 取video相关信息
     *
     * @param album
     * @param listener
     */
    public static void onGetVideo(int pageSize, int pageNo, Album album, OnGetVideoListener listener) {
        int siteId = album.getSite().getSiteId();
        switch (siteId) {
            case Site.LETV:
                new LetvApi().onGetVideo(album, pageSize, pageNo, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetVideo(album, pageSize, pageNo, listener);
                break;
        }
    }
    public static void onGetVideoPlayUrl(Video video, OnGetVideoPlayUrlListener listener) {
        int siteId = video.getSite();
        switch (siteId) {
            case Site.LETV:
                new LetvApi().onGetVideoPlayUrl(video,  listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetVideoPlayUrl(video,   listener);
                break;
        }
    }

}
