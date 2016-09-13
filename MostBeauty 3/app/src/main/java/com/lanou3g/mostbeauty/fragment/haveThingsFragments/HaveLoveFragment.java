package com.lanou3g.mostbeauty.fragment.haveThingsFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.base.BaseFragment;
import com.lanou3g.mostbeauty.liteOrm.Collect;
import com.lanou3g.mostbeauty.liteOrm.OrmTool;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class HaveLoveFragment extends BaseFragment {
    private List<Collect> collects;
    private GridView gridView;
    private HaveLoveAdapter adapter;

    @Override
    protected int initLayout() {
        return R.layout.fragment_have_love;

    }

    @Override
    protected void initView() {
        gridView = (GridView) getView().findViewById(R.id.fragment_have_love_cv);
        adapter = new HaveLoveAdapter(getContext());
    }

    @Override
    protected void initData() {
        collects = OrmTool.getInstance().getAllCollect();
        adapter.setCollects(collects);
        gridView.setAdapter(adapter);
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

    @Override
    public void onResume() {
        super.onResume();
        adapter.frashData();

    }

    public class HaveLoveAdapter extends BaseAdapter {
        private List<Collect > collects;
        private Context context;

        public HaveLoveAdapter(Context context) {
            this.context = context;

        }

        public void setCollects(List<Collect> collects) {
            this.collects = collects;
        }

        @Override
        public int getCount() {
            return collects.size();
        }

        @Override
        public Object getItem(int position) {
            return collects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HaveLoveFragmentViewHolder holder = null;
            if (convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_have_love,parent,false);
                holder = new HaveLoveFragmentViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (HaveLoveFragmentViewHolder) convertView.getTag();
            }
            // Glide.with(context).load(collects.get(position).getImgUrl()).into(holder.imageView);
            initGlide(holder.imageView,collects.get(position).getImgUrl());
            return convertView;

        }
        public class HaveLoveFragmentViewHolder{
            private ImageView imageView;
            public HaveLoveFragmentViewHolder(View view) {
                imageView = (ImageView) view.findViewById(R.id.adapter_have_love_iv);
            }
        }
        public void frashData(){
            notifyDataSetChanged();
        }

        public void initGlide(ImageView imageView,String url){
            Glide.with(context).load(url).priority(Priority.HIGH).thumbnail(0.1f).override(300,300).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);

        }
    }

}
