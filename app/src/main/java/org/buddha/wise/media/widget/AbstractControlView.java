package org.buddha.wise.media.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public abstract class AbstractControlView {
    protected ViewGroup mRoot;
    protected IMediaController mMediaController;

    protected void initViews() {

    }

    public void attachMediaController(IMediaController controller) {
        mMediaController = controller;
        mMediaController.attachView(this);
    }

    abstract void hide();

    abstract boolean isShowing();

    abstract void setAnchorView(View view);

    abstract void setEnabled(boolean enabled);

    abstract void show(int timeout);

    abstract void show();

    public View getView() {
        return mRoot;
    }

}
