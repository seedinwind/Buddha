package org.buddha.wise.vedio;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TableLayout;

import org.buddha.wise.R;
import org.buddha.wise.media.AndroidMediaController;
import org.buddha.wise.media.IjkVideoView;

/**
 * Created by Yuan Jiwei on 17/3/14.
 */

public class VideoActivity extends Activity{
    private IjkVideoView mVideoView;
    private AndroidMediaController mMediaController;
    private TableLayout mHudView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        mMediaController = new AndroidMediaController(this, false);

        mVideoView = (IjkVideoView) findViewById(R.id.video);
        mHudView=(TableLayout)findViewById(R.id.hud_view);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setHudView(mHudView);
//        videoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        mVideoView.setVideoURI(Uri.parse("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"));
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
