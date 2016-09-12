package com.lanou3g.mostbeauty.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION_CODES;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lanou3g.mostbeauty.Bean.HaveThingsHaveBean;
import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.HaveHaveActivity;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/8/31.
 */
@TargetApi(VERSION_CODES.M)
public class HaveThingsHaveItemAdapter extends BaseAdapter {
    private Context context;
    private HaveThingsHaveBean bean;
    private LinearLayout llLove,llCry;
    private ImageView ivLove,ivCry;
    private AnimationDrawable adLove,adCry;
//    private Message msg;
//    private Handler handler ;
    private TextView tvLove,tvCry;
    private PopupWindow popSmile, popCry;
//    private String imageUrl;
    private int id;
//    private List<Collect> collect;


    public void setBean(HaveThingsHaveBean bean) {
        this.bean = bean;
    }

    public HaveThingsHaveItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.getData().getActivities().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getActivities().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_have_things_have_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }

        String str  = bean.getData().getActivities().get(position).getDigest();
        holder.digest.setText(str);
        holder.designerName.setText(bean.getData().getActivities().get(position).getDesigner().getName());
        holder.desigerLabel.setText(bean.getData().getActivities().get(position).getDesigner().getLabel());
        initGlide(holder.images,bean.getData().getActivities().get(position).getImages().get(0));
        initGlide(holder.designerAvatar,bean.getData().getActivities().get(position).getDesigner().getAvatar_url());
        id = bean.getData().getActivities().get(position).getProduct().getId();
//        imageUrl = bean.getData().getActivities().get(position).getImages().get(0);

//        collect = OrmTool.getInstance().getAllCollect();
//        for (Collect collect : OrmTool.getInstance().getAllCollect()) {
//            if(collect.getIdUrl() ==id &&collect.getType()==Collect.TYPE_SMILE){
//                holder.images.setBackgroundResource(R.mipmap.like_10);
//                holder.smileLL.setBackgroundResource(R.drawable.shape_face_yellow);
//                holder.cry.setBackgroundResource(R.mipmap.dislike_1);
//                holder.cryLL.setBackgroundResource(R.drawable.shape_face);
//            }else if(collect.getIdUrl() ==id &&collect.getType()==Collect.TYPE_CRY){
//                holder.cry.setBackgroundResource(R.mipmap.dislike_9);
//                holder.cryLL.setBackgroundResource(R.drawable.shape_face_yellow);
//                holder.images.setBackgroundResource(R.mipmap.like_1);
//                holder.smileLL.setBackgroundResource(R.drawable.shape_face);
//
//            }
//
//        }


        holder.images.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HaveHaveActivity.class);
                intent.putExtra("haveId", id);
                context.startActivity(intent);
            }
        });
        final int heightSmile = bean.getData().getActivities().get(position).getProduct().getLike_user_num();
        final int heightCry = bean.getData().getActivities().get(position).getProduct().getUnlike_user_num();
        final ViewHolder finalHolder = holder;
        setPop(holder.smile,holder.cry,heightSmile,heightCry,finalHolder.smileLL,finalHolder.cryLL);
        return convertView;
    }

    public class ViewHolder{
        private LinearLayout smileLL,cryLL;
        private ImageView images,designerAvatar,smile,cry;
        private TextView digest,designerName,desigerLabel;
        public ViewHolder(View view) {
            smileLL= (LinearLayout) view.findViewById(R.id.adapter_have_things_have_item_smile_ll);
            cryLL= (LinearLayout) view.findViewById(R.id.adapter_have_things_have_item_cry_ll);
            images= (ImageView) view.findViewById(R.id.adapter_have_things_have_item_images);
            designerAvatar = (ImageView) view.findViewById(R.id.adapter_have_things_have_item_designer_avatar);
            digest= (TextView) view.findViewById(R.id.adapter_have_things_have_item_digest);
            designerName= (TextView) view.findViewById(R.id.adapter_have_things_have_item_designer_name);
            desigerLabel= (TextView) view.findViewById(R.id.adapter_have_things_have_item_desiger_label);
            smile= (ImageView) view.findViewById(R.id.adapter_have_things_have_item_smile);
            cry= (ImageView) view.findViewById(R.id.adapter_have_things_have_item_cry);
        }
    }
    public void initGlide(ImageView imageView,String url){
        Glide.with(context).load(url).priority(Priority.HIGH).thumbnail(0.1f).override(300,300).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .bitmapTransform(new CropCircleTransformation(context)).into(imageView);

    }


    public PopupWindow createPopLove(int height,int total){
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setHeight(height/2+200);
        popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_have_things_have_item_pop_love,null);
        llLove= (LinearLayout) view.findViewById(R.id.adapter_have_things_have_item_pop_love);
        ivLove= (ImageView) view.findViewById(R.id.adapter_have_things_have_item_pop_love_iv);
        adLove= (AnimationDrawable) ivLove.getBackground();
        adLove.setOneShot(true);
        tvLove= (TextView) view.findViewById(R.id.adapter_have_things_have_item_pop_love_tv);
        tvLove.setText(height*100/total+"%");
        popupWindow.setContentView(view);
        Drawable d = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(d);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop);
        return popupWindow;
    }
    public PopupWindow createPopCry(int height,int total){
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setHeight(height/2+200);
        popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_have_things_have_item_pop_cry,null);
        llCry= (LinearLayout) view.findViewById(R.id.adapter_have_things_have_item_pop_cry);
        ivCry= (ImageView) view.findViewById(R.id.adapter_have_things_have_item_pop_cry_iv);
        adCry= (AnimationDrawable) ivCry.getBackground();
        adCry.setOneShot(true);
        tvCry= (TextView) view.findViewById(R.id.adapter_have_things_have_item_pop_cry_tv);
        tvCry.setText(height*100/total+"%");
        popupWindow.setContentView(view);
        Drawable d = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(d);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop);
        return popupWindow;
    }
    private void setPop(final ImageView smile, final ImageView cry, final int heightSmile , final int heightCry, final LinearLayout smileLL, final LinearLayout cryLL) {
        smile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                 popCry = createPopCry(heightCry,heightCry+heightSmile);
                 popSmile =  createPopLove(heightSmile,heightCry+heightSmile);
                if (!popCry.isShowing()&&!popSmile.isShowing()) {
                    llLove.setBackgroundResource(R.drawable.shape_face_yellow);
                    llCry.setBackgroundResource(R.drawable.shape_face);
                    popCry.showAsDropDown(cry, 0, -(heightCry/2+200));
                    popSmile.showAsDropDown(smile, 0, -(heightSmile/2+200 ));
                    adLove.start();
//                    //删
//                    OrmTool.getInstance().deleteIdUrl( collect.get(0));
//                    //存
//                    OrmTool.getInstance().insertCollect(new Collect(imageUrl,id,Collect.TYPE_SMILE));

                    popSmile.setOnDismissListener(
                            new OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    smile.setBackgroundResource(R.mipmap.like_10);
                                    cry.setBackgroundResource(R.mipmap.dislike_1);
                                    //cry.setImageResource(R.mipmap.dislike_1);
                                    smileLL.setBackgroundResource(R.drawable.shape_face_yellow);
                                    cryLL.setBackgroundResource(R.drawable.shape_face);
                                }
                            });

                }

            }
        });
        cry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                 popCry = createPopCry(heightCry, heightCry+heightSmile);
                 popSmile =  createPopLove(heightSmile,heightCry+heightSmile);
                if (!popCry.isShowing()&&!popSmile.isShowing()) {
                    llLove.setBackgroundResource(R.drawable.shape_face);
                    llCry.setBackgroundResource(R.drawable.shape_face_yellow);
                    popCry.showAsDropDown(cry, 0, -(heightCry/2+200 ));
                    popSmile.showAsDropDown(smile, 0, -(heightSmile /2+200));
                    adCry.start();

//                    //删
//                    OrmTool.getInstance().deleteIdUrl(collect.get(0));
//                    //存
//                    OrmTool.getInstance().insertCollect(new Collect(imageUrl,id,Collect.TYPE_CRY));
                    popCry.setOnDismissListener(new OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            cry.setBackgroundResource(R.mipmap.dislike_9);
                            smile.setBackgroundResource(R.mipmap.like_1);
                            cryLL.setBackgroundResource(R.drawable.shape_face_yellow);
                            smileLL.setBackgroundResource(R.drawable.shape_face);
                        }
                    });


                }


            }
        });
    }








}
