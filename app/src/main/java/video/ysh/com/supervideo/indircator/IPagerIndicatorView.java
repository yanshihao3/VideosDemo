package video.ysh.com.supervideo.indircator;

import java.util.List;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 下午3:01
 * - @Email whynightcode@gmail.com
 */

public interface IPagerIndicatorView extends IPagerChangeListener {

    void setPostionDataList(List<PositionData> list);
}
