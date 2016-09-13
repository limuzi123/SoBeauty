package com.lanou3g.mostbeauty.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lanou3g.mostbeauty.Bean.HaveThingsReuseTitleBean;
import com.lanou3g.mostbeauty.fragment.haveThingsFragments.HaveFragment;
import com.lanou3g.mostbeauty.fragment.haveThingsFragments.HaveLoveFragment;
import com.lanou3g.mostbeauty.fragment.haveThingsFragments.ReuseFragment;

/**
 * title和viewpager
 * Created by dllo on 16/8/30.
 */
public class HaveThingsAdapter extends FragmentStatePagerAdapter {
    private static HaveThingsReuseTitleBean bean;
    public HaveThingsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setBean(HaveThingsReuseTitleBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (position==1){
            return HaveFragment.newInstance(position);
        }else if(position==0) {
            return HaveLoveFragment.newInstant(position);
        }else {
            return ReuseFragment.newInstant(position);
        }
    }

    @Override
    public int getCount() {
        return bean.getData().getCategories().size()+2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position==1){
            return "Daily";
        }else if (position == 0){
            return "我喜欢";
        }else {
            return bean.getData().getCategories().get(position-2).getName();
        }
    }
    //传值给ReuseFragment
    public static int getId(int position){
        int id = bean.getData().getCategories().get(position-2).getId();
        //标题位置position-2
        return id;
    }
    public static HaveThingsReuseTitleBean getHaveThingsReuseTitleBean(){
       return bean;
    }
}
