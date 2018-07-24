package video.ysh.com.supervideo.widget;

import android.content.Context;
import android.widget.LinearLayout;

import video.ysh.com.supervideo.R;


/**
 * Created by hejunlin on 17/4/15.
 */

public class LoadingView extends LinearLayout {

    public LoadingView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.loading_view_layout ,this);
    }
}
