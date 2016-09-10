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

    public AssistListAdapter(Context context) {
        this.context = context;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return likeList.size();
    }

    @Override
    public Object getItem(int position) {
        return likeList.get(position);
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
        holder.tvName.setText(likeList.get(position).getName());
        holder.tvContent.setText(likeList.get(position).getContent());
        Log.d("AssistListAdapter", "aaaaaaaa"+likeList.get(position).getContent());
        Glide.with(context).load(likeList.get(position).getImgUrl()).into(holder.imageView);
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
