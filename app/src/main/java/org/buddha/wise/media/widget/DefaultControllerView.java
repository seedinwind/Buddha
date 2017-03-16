package org.buddha.wise.media.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yuan Jiwei on 17/3/16.
 */

public class DefaultControllerView extends AbstractControlView {

    public DefaultControllerView(ViewGroup v) {
        mRoot = v;
    }

    @Override
    protected void initViews() {

    }

    @Override
    void hide() {
        mRoot.setVisibility(View.GONE);
    }

    @Override
    boolean isShowing() {
        return false;
    }

    @Override
    void setAnchorView(View view) {

    }

    @Override
    void setEnabled(boolean enabled) {

    }

    @Override
    void show(int timeout) {

    }

    @Override
    void show() {

    }
}
