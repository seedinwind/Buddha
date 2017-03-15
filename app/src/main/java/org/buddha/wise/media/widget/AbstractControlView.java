package org.buddha.wise.media.widget;

import android.view.View;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public abstract class AbstractControlView {
    private View v;
    private IMediaController mMediaController;

    protected abstract void initViews();

    public void attachMediaController(IMediaController controller) {
        mMediaController = controller;
    }
}
