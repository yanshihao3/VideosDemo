package video.ysh.com.supervideo.api;

import video.ysh.com.supervideo.bean.Site;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 上午10:12
 * - @Email whynightcode@gmail.com
 */
public class SiteApi {

    public void onGetChannelAlums(int siteId, int channelId,int pageNo,int pageSize,OnGetChannelAblumsListener listener) {
        switch (siteId) {
            case Site.LETV:
                new LetvApi().onGetChannelAlums(channelId,pageNo,pageSize,listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlums(channelId,pageNo,pageSize,listener);
                break;
        }
    }
}
