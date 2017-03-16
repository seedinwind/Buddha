package org.buddha.wise.vedio;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.buddha.wise.R;
import org.buddha.wise.media.widget.VideoView;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public class TestVideo extends Activity {
    private VideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vedio);
        mVideoView = (VideoView) findViewById(R.id.surface);
        mVideoView.setVideoURI(Uri.parse("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"));
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.playBackStop();
    }
}
