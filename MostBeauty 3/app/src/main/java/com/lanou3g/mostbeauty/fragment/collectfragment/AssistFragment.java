package com.lanou3g.mostbeauty.fragment.collectfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lanou3g.mostbeauty.Bean.AssistBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.adapter.AssistListAdapter;
import com.lanou3g.mostbeauty.base.BaseFragment;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;
import com.lanou3g.mostbeauty.liteOrm.Like;
import com.lanou3g.mostbeauty.liteOrm.OrmTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/2.
 */
public class AssistFragment extends BaseFragment{
    private ListView listView;
    private List<Like> likeList;
    private AssistListAdapter adapter;
    private LinearLayout linearLayout;
    @Override
    protected int initLayout() {
        return R.layout.assist_fragment;
    }

    @Override
    protected void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);
        linearLayout = (LinearLayout) getView().findViewById(R.id.line_small);

    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        String favorStr = intent.getStringExtra("favor");
        String idStr = intent.getStringExtra("idComment");

        adapter = new AssistListAdapter(getContext(), idStr);



        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            final int favor = Integer.parseInt(favorStr);
            getNetRequest(id, favor);
        }else {
            getListOrm();

        }
    }

    // TODO: 16/9/10 数据库数据
    private void getListOrm() {
        likeList = new ArrayList<>();
        likeList = OrmTool.getInstance().getAllLike();
        if (likeList.size() != 0) {

            adapter.setLikeList(likeList);
            listView.setAdapter(adapter);
            linearLayout.setVisibility(View.GONE);

        } else {

            listView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);

        }
    }

    // TODO: 16/9/10 获取网络数据
    private void getNetRequest(int id, final int favor) {
        NetTool.getInstance().startRequest(API.ASSIST_FRAGMENT_ONE + id + API.ASSIST_FRAGMENT_TWO, AssistBean.class,
                new onHttpCallBack<AssistBean>() {
                    @Override
                    public void onSuccess(AssistBean response) {
                        Log.d("AssistFragment", "response.getData().getArticles():" + response.getData());
                    if(favor>0){
                        adapter.setBean(response);

                        listView.setAdapter(adapter);
                        linearLayout.setVisibility(View.GONE);
                    }else {
                        listView.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);

                                              }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AssistFragment", "aaaaaaaxxxxx");

                    }
                });
    }
}
