package com.lanou3g.mostbeauty.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.mostbeauty.Bean.HaveThingsHaveBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.activity.HaveHaveActivity;
import com.lanou3g.mostbeauty.gson.NetTool;
import com.lanou3g.mostbeauty.gson.onHttpCallBack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by dllo on 16/8/31.
 * 布局只有一个ListView
 */
public class HaveThingsHaveAdapter extends BaseAdapter {

    private Context context;
    private String[] id;
    private HaveThingsHaveItemAdapter adapter;
    private DateFormat dateFormat , dfWay;
    private HaveThingsHaveChooseAdapter chooseAdapter;
    private int selectPosition;
    private int popId;
    private static ViewHolder holder = null;

    public HaveThingsHaveAdapter(Context context) {
        this.context = context;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return id[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_have_things_have, parent, false);
            holder = new ViewHolder(convertView,position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        getNetData(id[position], holder,position);
        return convertView;
    }


    public class ViewHolder {
        private ListView listView;
        private TextView date;
        private GridView gridView;
        private View footerView;

        public ViewHolder(View view,int position) {
            listView = (ListView) view.findViewById(R.id.adapter_have_things_have_lv);
            listView.setHeaderDividersEnabled(false);//删除分割线
            listView.setFooterDividersEnabled(false);
            // TODO: 16/8/31 都有头布局 写在这里方便赋值
            View headView = LayoutInflater.from(context).inflate(R.layout.adapter_have_things_have_head,null);
            date= (TextView) headView.findViewById(R.id.adapter_have_things_have_head_date);
            listView.addHeaderView(headView,null,true);
            if (position==0) {
                footerView = LayoutInflater.from(context).inflate(R.layout.adapter_have_things_have_foot, null);
                gridView= (GridView) footerView.findViewById(R.id.adapter_have_things_have_foot_cv);
                listView.addFooterView(footerView, null, true);
            }
        }
    }

    private void getNetData(String id, final ViewHolder holder, final int position) {
        String url = API.Have_Things_Have_Adapter +
                id +
                API.Have_Things_Have_Adapter_End;
        NetTool.getInstance().startRequest(url, HaveThingsHaveBean.class
                , new onHttpCallBack<HaveThingsHaveBean>() {
                    @Override
                    public void onSuccess(final HaveThingsHaveBean response) {
                        // TODO: 16/8/31 绑适配器
                        adapter = new HaveThingsHaveItemAdapter(context);
                        adapter.setBean(response);
                        holder.listView.setAdapter(adapter);
                        setListViewHeightBasedOnChildren(holder.listView);
                        long date = response.getData().getActivities().get(position).getPublish_at();
                        Date(date,holder);
                        // TODO: 16/8/31 listView第一个有尾布局
                        // TODO: 16/9/1 gridView绑适配器
                        if (position==0) {
                            chooseAdapter = new HaveThingsHaveChooseAdapter(context);
                            chooseAdapter.setBean(response);
                            holder.gridView.setAdapter(chooseAdapter);
                            holder.gridView.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    selectPosition = position;
                                    popId = response.getData().getActivities().get(selectPosition).getProduct().getId();
                                    Intent intent = new Intent(context,HaveHaveActivity.class);
                                    intent.putExtra("haveId", popId);
                                    intent.putExtra("havePosition",position);
                                    context.startActivity(intent);
                                }
                            });

                        }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /**
     * 重新计算高度
     * 一定要在setAdapter后调用
     *
     * @param listView
     */
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1)) + 5;
        listView.setLayoutParams(params);
    }

    /**
     * 获取时间
     * @param date
     * @param holder
     */
    public void Date(long date,ViewHolder holder){
        dateFormat = new SimpleDateFormat("yyyy.MM.dd,");
        dfWay = new SimpleDateFormat("EEEE");
        String dataStr = dateFormat.format(date);
        String mWay = dfWay.format(date);

        holder.date.setText(dataStr+mWay);
    }

    public  void setSelectGridView(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("best", Activity.MODE_PRIVATE);
        int position = sharedPreferences.getInt("havePosition",100);
        holder.gridView.getChildAt(position).setBackgroundColor(0xeeffffff);
    }

}
