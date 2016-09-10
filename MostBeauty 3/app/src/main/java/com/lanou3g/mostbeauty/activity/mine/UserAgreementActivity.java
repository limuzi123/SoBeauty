package com.lanou3g.mostbeauty.activity.mine;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.mostbeauty.R;
import com.lanou3g.mostbeauty.activity.API;
import com.lanou3g.mostbeauty.base.BaseActivity;

/**
 * Created by dllo on 16/9/9.
 */
public class UserAgreementActivity extends BaseActivity {
    private WebView webView;
    @Override
    protected int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
    webView = (WebView) findViewById(R.id.user_agreement);
    }

    @Override
    protected void initData() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(API.USER_AGREEMENT);
    }
}
