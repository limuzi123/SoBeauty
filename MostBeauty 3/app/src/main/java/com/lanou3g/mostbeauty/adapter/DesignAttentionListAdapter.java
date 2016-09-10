package com.lanou3g.mostbeauty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.liteOrm.Care;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/9/9.
 */
public class DesignAttentionListAdapter extends BaseAdapter {
    private List<Care> careList;
    private Context context;

    public DesignAttentionListAdapter(Context context) {
        this.context = context;
    }

    public void setCareList(List<Care> careList) {
        this.careList = careList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return careList.size();
    }

    @Override
    public Object getItem(int position) {
        return careList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_stylist_fragment_list,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(careList.get(position).getName());
        holder.tvSmall.setText(careList.get(position).getTitle());
        holder.tvCare.setVisibility(View.GONE);
        Glide.with(context).load(careList.get(position).getImgBigUrl()).into(holder.imgTop);

        Glide.with(context).load(careList.get(position).getImgNameUrl())
                .bitmapTransform(new CropCircleTransformation(context)).into(holder.imgName);
        return convertView;
    }
    class ViewHolder{
        private ImageView imgName,imgTop;
        private TextView tvName,tvSmall,tvCare;
        public ViewHolder(View view){
            imgName = (ImageView) view.findViewById(R.id.img_name);
            imgTop = (ImageView) view.findViewById(R.id.img_top);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvSmall = (TextView) view.findViewById(R.id.tv_small);
            tvCare = (TextView) view.findViewById(R.id.tv_care);

        }
    }
}
