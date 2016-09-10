package com.lanou3g.mostbeauty.fragment.haveThingsFragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.lanou3g.mostbeauty.Bean.HaveThingsReuseBean;
import com.lanou3g.mostbeauty.Bean.HaveThingsReuseTitleBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.adapter.HaveThingsAdapter;
import com.lanou3g.mostbeauty.adapter.HaveThingsReuseAdapter;
import com.lanou3g.mostbeauty.adapter.HaveThingsReusePopAdapter;
import com.lanou3g.mostbeauty.base.BaseFragment;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;

/**
 * Created by dllo on 16/8/30.
 */
public class ReuseFragment extends BaseFragment {
    private String start = API.REUSE_FRAGMENT_START;
    private String end = API.REUSE_FRAGMENT_END;
    private GridView gridView,gvPop;
    private HaveThingsReuseAdapter gvAdapter;
    private TextView textView;
    private ImageView imageView;
    private PopupWindow popupWindow;
    private LinearLayout linearLayout;
    private HaveThingsReuseTitleBean popBean;
    private HaveThingsReusePopAdapter popAdapter;
    private View view;
    private SharedPreferences sharedPreferences ;
    @Override
    protected int initLayout() {
        return R.layout.fragment_have_reuse;
    }

    @Override
    protected void initView() {
        sharedPreferences = getContext().getSharedPreferences("have", Activity.MODE_PRIVATE);
        gridView= (GridView) getView().findViewById(R.id.fragment_have_reuse_gv);
        gvAdapter=new HaveThingsReuseAdapter(getContext());
        textView= (TextView) getView().findViewById(R.id.fragment_have_reuse_tv);
        imageView= (ImageView) getView().findViewById(R.id.fragment_have_reuse_iv);
        popAdapter=new HaveThingsReusePopAdapter(getContext());
        linearLayout = (LinearLayout) getView().findViewById(R.id.fragment_have_reuse_ll);
        popBean= HaveThingsAdapter.getHaveThingsReuseTitleBean();
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_have_reuse_pop,null);
        gvPop = (GridView) view.findViewById(R.id.fragment_have_reuse_pop_gv);
    }

    @Override
    protected void initData() {
        int id= HaveThingsAdapter.getId(getPosition());


        searchText();
        if (sharedPreferences.getInt((getPosition()-1)+"",0)!=0){
            getNetData(sharedPreferences.getInt((getPosition()-1)+"",0));
        }else {
            getNetData(id);
        }
        popupWindow= CreatePop();
        if (sharedPreferences.getString((getPosition()-1)+"text","")!=""){
            textView.setText(sharedPreferences.getString((getPosition()-1)+"text",""));
        }
    }

    /**
     * 搜索框显示和隐藏 linearLayout点击事件
     */
    private void searchText() {
        if (popBean.getData().getCategories().get(getPosition()-1).getSub_categories()==null){
            linearLayout.setVisibility(View.GONE);

        }else {
            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!popupWindow.isShowing()) {
                       popShow();
//                        TextView popText = (TextView) view . findViewById(R.id.adapter_have_things_reuse_pop_btn);
//                        popText = (TextView) popAdapter.getItem(sharedPreferences.getInt((getPosition()-1)+"select",0));
//                        popText.set
                    } else {
                        popDismiss();

                    }
                }
            });
        }
        
    }

    private void popShow() {
        textView.setVisibility(View.INVISIBLE);
        imageView.setImageResource(R.mipmap.icon_category_fold);
        popupWindow.showAsDropDown(linearLayout);

    }

    private void popDismiss() {
        textView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.mipmap.icon_category_unfold);
        popupWindow.dismiss();
    }

    /**
     * 网络解析
     * @param id
     */
    protected void getNetData(int id){
        String url = start+id+end;
        NetTool.getInstance().startRequest(url, HaveThingsReuseBean.class, new onHttpCallBack<HaveThingsReuseBean>() {
            @Override
            public void onSuccess(HaveThingsReuseBean response) {
                gvAdapter.setHaveThingsReuseBean(response);
                gridView.setAdapter(gvAdapter);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * Fragment绑定TabLayout 用于Fragment的复用 在GuideViewPagerAdapter里调用
     * @param position
     * @return
     */
    public static ReuseFragment newInstant(int position){
        Bundle args=new Bundle();
        args.putInt("position",position);
        ReuseFragment fragment = new ReuseFragment();
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

    /**
     * 创建popupWindow
     * @return
     */
    private PopupWindow CreatePop(){
        final PopupWindow popupWindow = new PopupWindow(getContext());
        popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(LayoutParams.MATCH_PARENT);
        Drawable d = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(d);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);//设置焦点
        //适配器
        popAdapter.setHaveThingsReuseTitleBean(popBean);
        popAdapter.setItem(getPosition()-1);
        popAdapter.setSelectPosition(getPosition()-1);
        if (popBean.getData().getCategories().get(getPosition()-1).getSub_categories()!=null) {
            gvPop.setAdapter(popAdapter);
        }
        popupWindow.setContentView(view);
        popupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                popDismiss();
            }
        });
        popGridViewListener(popBean,getPosition()-1);
        return popupWindow;
    }

    /**
     * popupWindow里面GridView的点击监听
     */
    private void popGridViewListener(final HaveThingsReuseTitleBean response , final int item) {
        gvPop.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt((getPosition()-1)+"select",position);
                if (position==0){
                    popDismiss();
                    getNetData(HaveThingsAdapter.getId(getPosition()));
                    editor.putInt((getPosition()-1)+"", HaveThingsAdapter.getId(getPosition()));

                }else {
                    int idPop = popBean.getData().getCategories().get(getPosition() - 1).getSub_categories().get(position - 1).getId();
                    popDismiss();
                    getNetData(idPop);
                    editor.putInt((getPosition()-1)+"", idPop);
                    editor.putString((getPosition()-1)+"text",response.getData().getCategories().get(item).getSub_categories().get(position-1).getName());
                    textView.setText(response.getData().getCategories().get(item).getSub_categories().get(position-1).getName());
                }
                editor.apply();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBindDrawables(getView());
    }
    private void unBindDrawables(View view){
        if (view.getBackground()!=null){
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)){
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unBindDrawables(((ViewGroup) view).getChildAt(i));
            }((ViewGroup) view).removeAllViews();
        }
    }
}
