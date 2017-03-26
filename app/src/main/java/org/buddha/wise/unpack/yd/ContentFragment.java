package org.buddha.wise.unpack.yd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.buddha.wise.R;

/**
 * Created by seedinwind on 17/3/26.
 */

public class ContentFragment extends Fragment implements ContentContract.View{
    public  static int sCurrentSelectTab;
    private ContentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_yd_content,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static ContentFragment newFragment(Bundle b) {
        ContentFragment fragment=new ContentFragment();
        fragment.setArguments(b);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=getArguments();
        YDChannel channel= (YDChannel) b.get("channel");
        mPresenter=new ContentPresenter();
        mPresenter.getContentList(channel.getId());
    }
}
