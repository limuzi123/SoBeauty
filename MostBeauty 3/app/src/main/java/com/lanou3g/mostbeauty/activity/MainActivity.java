package com.lanou3g.mostbeauty.activity;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lanou3g.mostbeauty.R;

import com.lanou3g.mostbeauty.base.BaseActivity;
import com.lanou3g.mostbeauty.fragment.haveThingsFragments.HaveThingsFragment;
import com.lanou3g.mostbeauty.fragment.MyFragment;
import com.lanou3g.mostbeauty.fragment.pictorialfragment.PictorialFragment;
import com.lanou3g.mostbeauty.fragment.StylistFragment;
import com.lanou3g.mostbeauty.other.MusicService;

public class MainActivity extends BaseActivity implements OnCheckedChangeListener {
    //背景音乐的开关
    private ImageView imageViewMusic;
    //用于开关背景音乐的flag
    private boolean flag = true;
    private RadioGroup radioGroup;
    private PictorialFragment pictorial;
    private RadioButton button;

    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.main_radio);
        button = (RadioButton) findViewById(R.id.pictal_radio);
        radioGroup.setOnCheckedChangeListener(this);
        imageViewMusic = (ImageView) findViewById(R.id.image_music);

    }

    @Override
    protected void initData() {
        pictorial = new PictorialFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, pictorial).commit();
        button.setChecked(true);
        //启动背景音乐
        Log.e("start","intent");
        final Intent intent = new Intent(MainActivity.this, MusicService.class);
        startService(intent);
        imageViewMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onclick","intent转移");
                Intent intents = new Intent(MainActivity.this,MusicService.class);
                if (!flag){
                    startService(intents);
                    flag = true;
                }else {
                    stopService(intent);
                    flag = false;
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.pictal_radio:
                pictorial = new PictorialFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, pictorial).commit();
                break;
            case R.id.havething_radio:
                HaveThingsFragment haveThings = new HaveThingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, haveThings).commit();
                break;
            case R.id.stailst_radio:
                StylistFragment stylist = new StylistFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, stylist).commit();
                break;
            case R.id.my_radio:
                MyFragment my = new MyFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, my).commit();
                break;
        }
    }
    }
