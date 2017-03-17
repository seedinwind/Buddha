package org.buddha.wise.media.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.buddha.wise.R;

/**
 * Created by Yuan Jiwei on 17/3/16.
 */

public class DefaultControllerView extends AbstractControlView implements View.OnClickListener {

    private ImageButton mPlayControl;

    public DefaultControllerView(ViewGroup v) {
        mRoot = v;
        initViews();
    }

    @Override
    protected void initViews() {
        mPlayControl = (ImageButton) mRoot.findViewById(R.id.iv_toggle);
        mPlayControl.setOnClickListener(this);
    }

    @Override
    void hide() {
        mRoot.setVisibility(View.GONE);
    }

    @Override
    boolean isShowing() {
        return mRoot.getVisibility() == View.VISIBLE;
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
        mRoot.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toggle:
                if (mMediaController != null) {
                    if (mMediaController.canStart()) {
                        mMediaController.start();
                        mPlayControl.setImageResource(R.mipmap.icon_pause);
                        hide();
                    } else if (mMediaController.isPlaying()) {
                        mMediaController.pause();
                        mPlayControl.setImageResource(R.mipmap.icon_play);
                        hide();
                    }
                }
        }
    }
}
