package org.buddha.wise.media.widget;

import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import org.buddha.wise.R;

/**
 * Created by Yuan Jiwei on 17/3/16.
 */

public class DefaultControllerView extends AbstractControlView implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageButton mTooglePlay;
    private AppCompatSeekBar mSeek;
    private int mCurrentProgress;

    public DefaultControllerView(ViewGroup v) {
        mRoot = v;
        initViews();
    }

    @Override
    protected void initViews() {
        mTooglePlay = (ImageButton) mRoot.findViewById(R.id.iv_toggle);
        mTooglePlay.setOnClickListener(this);
        mSeek = (AppCompatSeekBar) mRoot.findViewById(R.id.seek);
        mSeek.setOnSeekBarChangeListener(this);
    }

    @Override
    void hide() {
        mRoot.setVisibility(View.GONE);
    }

    @Override
    void initSeekBar(int max) {
        mSeek.setMax(max);
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
        if (timeout <= 0) {
            timeout = 1000;
        }
        show();
        mRoot.postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        }, timeout);
    }

    @Override
    void show() {
        mRoot.setVisibility(View.VISIBLE);
        mSeek.setProgress(mMediaController.getCurrentPosition());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toggle:
                if (mMediaController != null) {
                    if (mMediaController.canStart()) {
                        mMediaController.start();
                        mTooglePlay.setImageResource(R.mipmap.icon_pause);
                        mRoot.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hide();
                            }
                        }, 1500);

                    } else if (mMediaController.isPlaying()) {
                        mMediaController.pause();
                        mTooglePlay.setImageResource(R.mipmap.icon_play);
                        mRoot.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hide();
                            }
                        }, 1500);
                    }
                }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mCurrentProgress = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaController.seekTo(mCurrentProgress);
        mRoot.postDelayed(new Runnable() {
            @Override
            public void run() {
                hide();
            }
        }, 1500);
    }
}
