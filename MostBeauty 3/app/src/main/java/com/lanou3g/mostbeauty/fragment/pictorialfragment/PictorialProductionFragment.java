package com.lanou3g.mostbeauty.fragment.pictorialfragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lanou3g.mostbeauty.Bean.PictorialActivityBean;
import com.lanou3g.mostbeauty.Bean.ProductionBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.adapter.PictorialActivityGridAdapter;
import com.lanou3g.mostbeauty.adapter.PictorialProductionGridAdapter;
import com.lanou3g.mostbeauty.base.BaseFragment;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;
import com.lanou3g.mostbeauty.myview.StationGridview;

/**
 * Created by dllo on 16/9/3.
 */
public class PictorialProductionFragment extends BaseFragment {
    private StationGridview gridView;
    private PictorialActivityGridAdapter gridAdapter;
    private PictorialProductionGridAdapter adapter;

    private String string,str;
    private int idSy,id,id1;

    @Override
    protected int initLayout() {
        return R.layout.fragment_production;
    }

    @Override
    protected void initView() {
        gridView = (StationGridview)getView().findViewById(R.id.grid_view);

    }

    @Override
    protected void initData() {
        gridAdapter = new PictorialActivityGridAdapter(getContext());
        adapter= new PictorialProductionGridAdapter(getContext());
        Intent intent = getActivity().getIntent();
        str = intent.getStringExtra("id");
        string = intent.getStringExtra("idStylist");
        if(str != null){

            id = Integer.parseInt(str);
            getNetRequest(id);

        }else if(string != null){


            idSy = Integer.parseInt(string);

            getNetRequestSy(idSy);

        }





    }

    // TODO: 16/9/6 获取网络数据
    private void getNetRequest(int id) {
        NetTool.getInstance().startRequest(API.PICTORIAL_ACTIVITY_ONE + id + API.PICTORIAL_ACTIVITY_TWO,
                PictorialActivityBean.class, new onHttpCallBack<PictorialActivityBean>() {
                    @Override
                    public void onSuccess(PictorialActivityBean response) {

                        gridView.setVisibility(View.VISIBLE);
                        gridAdapter.setBean(response);
                        gridView.setAdapter(gridAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    private void getNetRequestSy(int idSy) {
        NetTool.getInstance().startRequest(API.PICTORIAL_PRODUCTION_ONE + idSy + API.PICTORIAL_PRODUCTION_TWO,
                ProductionBean.class, new onHttpCallBack<ProductionBean>() {
                    @Override
                    public void onSuccess(ProductionBean response) {

                        gridView.setVisibility(View.VISIBLE);
                        adapter.setBean(response);
                        gridView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

}
