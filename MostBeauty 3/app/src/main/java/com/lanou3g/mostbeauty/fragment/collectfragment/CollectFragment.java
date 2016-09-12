package com.lanou3g.mostbeauty.fragment.collectfragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lanou3g.mostbeauty.Bean.CollectBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.adapter.CollectListAdapter;
import com.lanou3g.mostbeauty.base.BaseFragment;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;

/**
 * Created by dllo on 16/9/2.
 */
public class CollectFragment extends BaseFragment{
    private ListView listView;
    private CollectListAdapter adapter;
    private LinearLayout linearLayout;
    private int id;

    @Override
    protected int initLayout() {

        return R.layout.collect_fragment;
    }

    @Override
    protected void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);
        linearLayout = (LinearLayout) getView().findViewById(R.id.line_small);


    }

    @Override
    protected void initData() {
        adapter = new CollectListAdapter(getContext());
        Intent intent = getActivity().getIntent();
        String likeStr = intent.getStringExtra("like");
        String idStr = intent.getStringExtra("idComment");
        if(idStr != null){
            id = Integer.parseInt(idStr);
            final int like = Integer.parseInt(likeStr);
            NetTool.getInstance().startRequest(API.COLLECT_FRAGEMNT_ONE + id + API.COLLECT_FRAGMENT_TWO, CollectBean.class,
                    new onHttpCallBack<CollectBean>() {
                        @Override
                        public void onSuccess(CollectBean response) {
                            if(like>0){
                                adapter.setBean(response);
                                listView.setAdapter(adapter);
                                linearLayout.setVisibility(View.GONE);
                            }else {
                                linearLayout.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });

        }





    }
}
