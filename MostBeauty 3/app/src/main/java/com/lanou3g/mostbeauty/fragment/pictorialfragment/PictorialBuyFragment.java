package com.lanou3g.mostbeauty.fragment.pictorialfragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeauty.Bean.StoreBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.base.BaseFragment;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;

/**
 * Created by dllo on 16/9/3.
 */
public class PictorialBuyFragment extends BaseFragment {
    private TextView tvBuy;
    private ImageView imgName;
    private String str,string;
    private int id1,idSy,id;


    @Override
    protected int initLayout() {
        return R.layout.fragment_pictorial_buy;
    }

    @Override
    protected void initView() {
        tvBuy = (TextView) getView().findViewById(R.id.tv_buy);
        imgName = (ImageView) getView().findViewById(R.id.img_name);

    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        str = intent.getStringExtra("idAuthor");
        string = intent.getStringExtra("idStylist");
        if(str!=null){
            id1 = Integer.parseInt(str);
            id=id1;
        }else if(string!= null){
            idSy = Integer.parseInt(string);
            id=idSy;
        }


        NetTool.getInstance().startRequest(API.PICTORIAL_STORE_FRAGMENT_ONE + id + API.PICTORIAL_STORE_FRAGMENT_TWO, StoreBean.class,
                new onHttpCallBack<StoreBean>() {
                    @Override
                    public void onSuccess(StoreBean response) {
                        tvBuy.setText(response.getData().getOnline_shops().get(0).getName());

                        Glide.with(getContext()).load(response.getData().getOnline_shop_image()).into(imgName);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }


}
