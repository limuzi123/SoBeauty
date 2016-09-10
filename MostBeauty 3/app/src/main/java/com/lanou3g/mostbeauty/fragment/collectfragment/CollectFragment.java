package com.lanou3g.mostbeauty.fragment.collectfragment;

import android.widget.ListView;

import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.base.BaseFragment;

/**
 * Created by dllo on 16/9/2.
 */
public class CollectFragment extends BaseFragment{
    private ListView listView;
    @Override
    protected int initLayout() {

        return R.layout.collect_fragment;
    }

    @Override
    protected void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);

    }

    @Override
    protected void initData() {

    }
}
