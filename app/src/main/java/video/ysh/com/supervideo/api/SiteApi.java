package video.ysh.com.supervideo.api;

import video.ysh.com.supervideo.bean.Channel;
import video.ysh.com.supervideo.bean.Site;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 上午10:12
 * - @Email whynightcode@gmail.com
 */
public class SiteApi {

    public  static void onGetChannelAlums(int siteId, Channel channelId, int pageNo, int pageSize, OnGetChannelAlbumListener listener) {
        switch (siteId) {
            case Site.LETV:
                new LetvApi().onGetChannelAlbums(channelId,pageNo,pageSize,listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlbums(channelId,pageNo,pageSize,listener);
                break;
        }
    }
}
