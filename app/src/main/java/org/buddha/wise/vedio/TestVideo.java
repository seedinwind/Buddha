package org.buddha.wise.vedio;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import org.buddha.wise.R;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public class TestVideo extends Activity implements SurfaceHolder.Callback{
    private SurfaceView mSurfaceView;
    IMediaPlayer mIMediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vedio);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface);
        mIMediaPlayer = new IjkMediaPlayer();
        mSurfaceView.getHolder().addCallback(this);
        try {
            mIMediaPlayer.setDataSource(this, Uri.parse("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mIMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Toast.makeText(TestVideo.this, "Prepared", Toast.LENGTH_SHORT).show();
            }
        });
        mIMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Toast.makeText(TestVideo.this, "onError", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mIMediaPlayer.prepareAsync();
        mIMediaPlayer.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIMediaPlayer.setDisplay(mSurfaceView.getHolder());

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
