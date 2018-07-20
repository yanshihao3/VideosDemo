package video.ysh.com.supervideo.indircator;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 下午3:01
 * - @Email whynightcode@gmail.com
 */

public interface IPagerIndicatorLayout extends IPagerChangeListener{

    /**
     * 添加到CoolIndicatorLayout上
     */
    void onAttachCoolIndicatorLayout();

    /**
     * 从CoolIndicatorLayout下移除
     */
    void onDetachCoolIndicatorLayout();
}
