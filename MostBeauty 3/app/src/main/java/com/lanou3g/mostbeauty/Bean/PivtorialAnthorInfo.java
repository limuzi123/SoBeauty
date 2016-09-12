package com.lanou3g.mostbeauty.Bean;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.activity.pictorial.PictorialActivity;
import com.lanou3g.mostbeauty.activity.pictorial.PictorialAuthorActivity;
import com.lanou3g.mostbeauty.fragment.pictorialfragment.PictorialBuyFragment;
import com.lanou3g.mostbeauty.fragment.pictorialfragment.PictorialProductionFragment;
import com.lanou3g.mostbeauty.fragment.pictorialfragment.PictorialSmallFragment;
import com.lanou3g.mostbeauty.fragment.pictorialfragment.PictorialStoreFragment;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/3.
 */
public class PivtorialAnthorInfo {
    private String title;
    private Fragment fragment;

    private static List<PivtorialAnthorInfo> infos;


    public PivtorialAnthorInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public static List<PivtorialAnthorInfo> getTabInfos() {
        infos = new ArrayList<>();

        infos.add(new PivtorialAnthorInfo("作品", new PictorialProductionFragment()));
        if(PictorialAuthorActivity.getResponseTwo().getData().getId()!=PictorialAuthorActivity.getId()){
            infos.add(new PivtorialAnthorInfo("画报", new PictorialSmallFragment()));
        }
       if (PictorialAuthorActivity.getResponse().getData().getShops().size() != 0) {
            infos.add(new PivtorialAnthorInfo("旗舰门店", new PictorialStoreFragment()));
       }
        infos.add(new PivtorialAnthorInfo("线上购买", new PictorialBuyFragment()));
        return infos;
    }

}
