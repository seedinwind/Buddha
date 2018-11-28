package org.buddha.wise.vedio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.buddha.wise.R;

import java.util.ArrayList;
import java.util.List;

public class SSYDListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssyd);
        recyclerView=findViewById(R.id.list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SSYDListAdapter adapter=new SSYDListAdapter();
        recyclerView.setAdapter(adapter);
        List<String> data=createData();
        adapter.addVedioList(data);
    }

    private List<String> createData() {
        List<String> res=new ArrayList<>();
        String baseUrl="http://47.94.95.216/";
        String temp="";
        for (int i=1;i<157;i++){
            temp="19-014-";
            if(i/10==0){
                temp=temp+"000"+i;
            }else if(i/100==0){
                temp=temp+"00"+i;
            }else if(i/1000==0){
                temp=temp+"0"+i;
            }
            res.add(baseUrl+temp+".mp4");
        }
        return res;
    }
}
