package video.ysh.com.supervideo.api;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */
public abstract class BaseSiteApi {
    abstract void onGetChannelAlums(int channel,int pageNo,int pageSize,OnGetChannelAblumsListener onGetChannelAblumsListener);
}
