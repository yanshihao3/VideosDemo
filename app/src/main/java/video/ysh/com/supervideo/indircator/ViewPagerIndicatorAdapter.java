package video.ysh.com.supervideo.indircator;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;


/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/19 下午3:01
 * - @Email whynightcode@gmail.com
 */

public abstract class ViewPagerIndicatorAdapter {

    public abstract int getCount();
    public abstract IPagerTitle getTitle(Context context, int index);
    public abstract IPagerIndicatorView getIndicator(Context conext);

    public float getTitleWeight(){
        return 1;
    }

    private final DataSetObservable mDataSetObservable = new DataSetObservable();

    public final void registerDataSetObservable(DataSetObserver Observable) {
        mDataSetObservable.registerObserver(Observable);
    }

    public final void unregisterDataSetObservable(DataSetObserver Observable) {
        mDataSetObservable.unregisterObserver(Observable);
    }

    public final void notifySetDataChanged() {
        mDataSetObservable.notifyChanged();
    }
}
