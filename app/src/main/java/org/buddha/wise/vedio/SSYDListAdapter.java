package org.buddha.wise.vedio;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.buddha.wise.R;

import java.util.ArrayList;
import java.util.List;

class SSYDListAdapter extends RecyclerView.Adapter {
    private List<String> data=new ArrayList();

    public void addVedioList(List<String> list){
        data.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListItemHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
         ListItemHolder itemholder= (ListItemHolder) holder;
        itemholder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),VideoActivity.class);
                intent.putExtra("url",data.get(position));
                v.getContext().startActivity(intent);
            }
        });
        itemholder.title.setText(itemholder.title.getContext().getString(R.string.item_format,position+1));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class ListItemHolder extends RecyclerView.ViewHolder{
    TextView title;
    public ListItemHolder(View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.item);
    }
}