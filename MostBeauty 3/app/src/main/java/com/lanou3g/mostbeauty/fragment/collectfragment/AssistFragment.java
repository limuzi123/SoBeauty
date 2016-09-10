package com.lanou3g.mostbeauty.fragment.collectfragment;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.adapter.AssistListAdapter;
import com.lanou3g.mostbeauty.base.BaseFragment;
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

        adapter = new AssistListAdapter(getContext());

        likeList = new ArrayList<>();
        likeList = OrmTool.getInstance().getAllLike();
        if(likeList.size()!=0) {
            Log.d("AssistFragment", "aaaaaaaaxxx" + likeList);
            adapter.setLikeList(likeList);
            listView.setAdapter(adapter);
            linearLayout.setVisibility(View.GONE);

        }else {

            listView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);

        }
    }
}
