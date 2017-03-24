package org.buddha.wise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.xiaohujr.credit.sdk.net.entity.collections.ParamMapBuilder;
import com.xiaohujr.credit.sdk.net.net.LogicRequest;
import com.xiaohujr.credit.sdk.net.net.RequestMethod;
import com.xiaohujr.credit.sdk.net.net.SydHttpError;
import com.xiaohujr.credit.sdk.net.net.handle.ResponseHandler;

import org.buddha.wise.unpack.yd.Util;
import org.buddha.wise.vedio.TestVideo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestVideo.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String name = Util.createGuest();
        String password = Util.createCoded(name.toLowerCase(), name);
        String secret = Util.createCoded(name.toLowerCase(), "yidian");
        Log.e("yidian--name", name);
        Log.e("yidian--password", password);
        Log.e("yidian--secret", secret);
        String url = "http://a1.go2yd.com/Website/user/login-as-guest?"
                + "platform=1"
                + "&deviceId="
                + "&cv="
                + "&token="
                + "&distribution="
                + "&autoStartup="
                + "&appid=yidian"
                + "&version=020118"
                + "&net=wifi"
                + "&username=" + name
                + "&password=" + password
                + "&secret=" + secret;
        new MyRequest().url(url)
                .method(RequestMethod.POST)
//                .postParams(new ParamMapBuilder()
//                .putValue("platform","1")
//                .putValue("deviceId","")
//                .putValue("cv","")
//                .putValue("token","")
//                .putValue("distribution","")
//                .putValue("autoStartup","")
//                .putValue("appid","yidian")
//                .putValue("version","020118")
//                .putValue("net","wifi")
//                .putValue("username",name)
//                .putValue("password",password)
//                .putValue("secret",secret))
                .post(new ResponseHandler<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.e("Json", jsonObject.toJSONString());
                    }

                    @Override
                    public void onFail(SydHttpError sydHttpError) {

                    }
                });
        String log = "http://a1.go2yd.com/Website/user/login-as-guest?platform=1&deviceId=&password=5f052799d5124c4e13aa47a63384d306c6f1e9d8&cv=&secret=1da24d87b27a6dbcb1fa4174174a3cf6bce32f44&username=HG_6848e7cb-eed5-416f-842c-879c2da352a7&token=&distribution=&appid=yidian&autoStartup=&version=020118&net=wifi";
        String channel = "https://118.26.223.139/Website/channel/news-list-for-channel?platform=1&cv=3.9.2.0&fields=docid&fields=date&fields=image&fields=image_urls&fields=like&fields=source&fields=title&fields=url&fields=comment_count&fields=up&fields=down&cend=50&infinite=true&distribution=vivo1&refresh=1&appid=yidian&channel_id=11410718704&cstart=0&group_fromid=g181&version=020118&ad_version=010935&net=wifi";
        String search_channel = "https://118.26.223.139/Website/channel/search-channel?platform=1&appid=yidian&word=%E4%BD%9B%E6%95%99&cv=3.9.2.0&group_id=100762069024&group_fromid=g181&distribution=vivo1&version=020118&net=wifi";
        String dingyue = "https://118.26.223.139/Website/channel/create?platform=1&appid=yidian&cv=3.9.2.0&distribution=vivo1&version=020118&net=wifi";
        String content = "https://118.26.223.139/Website/contents/content?platform=1&cv=3.9.2.0&related_navigations=true&distribution=vivo1&appid=yidian&related_docs=true&bottom_channels=true&related_wemedia=true&docid=0Fr873h4&vertical_card=true&version=020118&net=wifi";
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
