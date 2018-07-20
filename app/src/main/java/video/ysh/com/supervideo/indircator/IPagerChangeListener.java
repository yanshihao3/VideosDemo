package video.ysh.com.supervideo.indircator;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 下午3:01
 * - @Email whynightcode@gmail.com
 */
public interface IPagerChangeListener {

    //选中的
    void onPagerSelected(int position);

    /**
     * 当页面滑动时回调
     *
     * @param position              位置
     * @param positionOffsetPercent 0.0f-1.0f 滚动百分比
     * @param positionOffsetPixel   距离
     */
    void onPagerScrolled(int position, float positionOffsetPercent, int positionOffsetPixel);


    /**
     * 页面滑动状态发生变化时回调
     * 如从静止到滑动,或滑动到静止
     *
     * @param position
     */
    void onPagerScrollStateChanged(int position);
}
