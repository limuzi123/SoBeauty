package com.lanou3g.mostbeauty.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeauty.Bean.AssistBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.liteOrm.Collect;
import com.lanou3g.mostbeauty.liteOrm.Like;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class AssistListAdapter extends BaseAdapter{
    private List<Like> likeList;
    private Context context;
    private String idStr;
    private AssistBean  bean;

    public AssistListAdapter(Context context,String idStr) {
        this.context = context;
        this.idStr = idStr;
    }

    public void setBean(AssistBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(idStr != null){
            return bean.getData().getArticles().size();
        }else {
            return likeList.size();
        }


    }

    @Override
    public Object getItem(int position) {
        if(idStr != null){
            return bean.getData().getArticles().get(position);
        }else {
            return likeList.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView== null){
           convertView = LayoutInflater.from(context).inflate(R.layout.item_assist_list,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();

        }
        if(idStr != null){
            holder.tvName.setText(bean.getData().getArticles().get(position).getTitle());
            holder.tvContent.setText(bean.getData().getArticles().get(position).getSub_title());
            Glide.with(context).load(bean.getData().getArticles().get(position).getImage_url()).into(holder.imageView);
        }else {
            holder.tvName.setText(likeList.get(position).getName());
            holder.tvContent.setText(likeList.get(position).getContent());
            Log.d("AssistListAdapter", "aaaaaaaa"+likeList.get(position).getContent());
            Glide.with(context).load(likeList.get(position).getImgUrl()).into(holder.imageView);
        }

        return convertView;
    }
    class ViewHolder{
        private TextView tvName,tvContent;
        private ImageView imageView;
        public ViewHolder(View view) {
            tvContent = (TextView) view.findViewById(R.id.tv_content);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            imageView = (ImageView) view.findViewById(R.id.img_name);
        }
    }
}
