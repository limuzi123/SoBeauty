package com.lanou3g.mostbeauty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeauty.Bean.CollectBean;
import com.lanou3g.mostbeauty.R;

/**
 * Created by dllo on 16/9/10.
 */
public class CollectListAdapter extends BaseAdapter {
    private Context context;
    private CollectBean bean;

    public CollectListAdapter(Context context) {
        this.context = context;
    }

    public void setBean(CollectBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().getArticles().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getArticles().get(position);
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
        holder.tvName.setText(bean.getData().getArticles().get(position).getTitle());
        holder.tvContent.setText(bean.getData().getArticles().get(position).getSub_title());
        Glide.with(context).load(bean.getData().getArticles().get(position).getImage_url()).into(holder.imageView);
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
