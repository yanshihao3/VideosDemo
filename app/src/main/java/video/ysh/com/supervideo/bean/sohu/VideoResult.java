package video.ysh.com.supervideo.bean.sohu;

import com.google.gson.annotations.Expose;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

/**
 * VideoResult 表示详情页面放回Video,一级结构,包含VideoData
 */
public class VideoResult {

    @Expose
    private long status;

    @Expose
    private String statusText;

    @Expose
    private VideoData data;

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

    public VideoData getData() {
        return data;
    }

    public void setData(VideoData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VideoResult{" +
                "status=" + status +
                ", statusText='" + statusText + '\'' +
                ", data=" + data +
                '}';
    }
}
