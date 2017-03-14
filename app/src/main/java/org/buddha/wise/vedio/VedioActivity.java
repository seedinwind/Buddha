package org.buddha.wise.vedio;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.buddha.wise.R;
import org.buddha.wise.media.IRenderView;
import org.buddha.wise.media.IjkVideoView;

/**
 * Created by Yuan Jiwei on 17/3/14.
 */

public class VedioActivity extends Activity{
    private IjkVideoView videoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio);
        videoView = (IjkVideoView) findViewById(R.id.vedio);
        videoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        videoView.setVideoURI(Uri.parse("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"));
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
