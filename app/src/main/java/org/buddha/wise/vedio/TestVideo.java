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
        mVideoView.setVideoURI(Uri.parse("http://119.188.38.112/youku/69772E378383F82F18D40461A9/03000205014FB8289134D006A37D5D650E3144-A684-9865-26E6-60004ACDE06D.flv"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.playBackStop();
    }
}
