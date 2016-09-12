package com.lanou3g.mostbeauty.fragment.haveThingsFragments;

import android.os.Bundle;

import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.base.BaseFragment;

/**
 * Created by dllo on 16/9/12.
 */
public class HaveLoveFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.fragment_have_love;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    /**
     * Fragment绑定TabLayout 用于Fragment的复用 在GuideViewPagerAdapter里调用
     * @param position
     * @return
     */
    public static HaveLoveFragment newInstant(int position){
        Bundle args=new Bundle();
        args.putInt("position",position);
        HaveLoveFragment fragment = new HaveLoveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 获取Fragment绑定的TabLayout 在GuideViewPagerAdapter里调用 对应的标题位置
     * @return
     */
    private int getPosition(){
        int position;
        Bundle args=getArguments();
        //获得传入Bundle的位置信息 并取出
        if (args!=null) {
            position = args.getInt("position");
            return position;
        }else return 0;
    }
}
