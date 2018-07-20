package video.ysh.com.supervideo.bean.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午7:04
 * - @Email whynightcode@gmail.com
 */

/**
 * 搜狐数据频道数据返回集
 */
public class Result {

    @Expose
    private long status;

    @Expose
    private String statusText;

    //for 列表页
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
