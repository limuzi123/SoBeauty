package com.lanou3g.mostbeauty.fragment.haveThingsFragments;

import android.os.Bundle;
import android.widget.ListView;

import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.adapter.HaveThingsHaveAdapter;
import com.lanou3g.mostbeauty.base.BaseFragment;

/**
 * Created by dllo on 16/8/30.
 * 实体类: HaveThingsHaveBean
 * listView套用listView
 */
public class HaveFragment extends BaseFragment {
    private ListView listView;
    private HaveThingsHaveAdapter adapter;
    private String[] id= {"1472529600000","1472400000000","1472270400000"};
    @Override
    protected int initLayout() {
        return R.layout.fragment_have_have;
    }

    @Override
    protected void initView() {
        listView= (ListView) getView().findViewById(R.id.have_have_lv);
        adapter = new HaveThingsHaveAdapter(getContext());
    }

    @Override
    protected void initData() {
        adapter.setId(id);
        listView.setAdapter(adapter);

    }
    public static HaveFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt("position",position);
        HaveFragment fragment = new HaveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 减小缓存方法 适用于Fragment
     * 但素 好像没什么用
     */
//    @Override
//    public void onDestroyView() {
//        unBindDrawables(getView());
//    }
//    private void unBindDrawables(View view){
//        if (view.getBackground()!=null){
//            view.getBackground().setCallback(null);
//        }
//        if (view instanceof ViewGroup && !(view instanceof AdapterView)){
//            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
//                unBindDrawables(((ViewGroup) view).getChildAt(i));
//            }((ViewGroup) view).removeAllViews();
//        }
//    }



//    @Override
//    public void onResume() {
//        super.onResume();
//        listView.setAdapter(adapter);
//    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.frashData();
    }
}
