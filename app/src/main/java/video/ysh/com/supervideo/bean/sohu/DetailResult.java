package video.ysh.com.supervideo.bean.sohu;

import com.google.gson.annotations.Expose;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */
/**
 * 搜狐数据频道数据返回集
 */
public class DetailResult {

    @Expose
    private long status;

    @Expose
    private String statusText;

    //for 详情页
    @Expose
    private ResultAlbum data;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public ResultAlbum getResultAlbum() {
        return data;
    }

    public void setResultAlbum(ResultAlbum resultAlbum) {
        this.data = resultAlbum;
    }
}
