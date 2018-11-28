package org.buddha.wise.vedio;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.buddha.wise.R;

/**
 * Created by Yuan Jiwei on 17/3/15.
 */

public class TestVideo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vedio);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
