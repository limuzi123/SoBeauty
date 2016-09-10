package com.lanou3g.mostbeauty.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.adapter.DesignAttentionListAdapter;
import com.lanou3g.mostbeauty.base.BaseActivity;
import com.lanou3g.mostbeauty.liteOrm.Care;
import com.lanou3g.mostbeauty.liteOrm.OrmTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/2.
 */
//关注的设计师
public class DesignAttentionActivity extends BaseActivity{
    private ImageView imageViewDesignBack;
    private ListView listView;
    private List<Care> careList;
    private DesignAttentionListAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_design;
    }

    @Override
    protected void initView() {
     imageViewDesignBack = (ImageView) findViewById(R.id.image_back_material);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    protected void initData() {
        careList = new ArrayList<>();
        adapter = new DesignAttentionListAdapter(this);
        careList = OrmTool.getInstance().getAllCare();
        adapter.setCareList(careList);
        listView.setAdapter(adapter);


     imageViewDesignBack.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             finish();
         }
     });
    }
}
