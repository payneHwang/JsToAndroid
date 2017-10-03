package com.js2And.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.js2And.R;
import com.js2And.client.ActionWebChromeClient;
import com.js2And.client.ActionWebViewClient;
import com.js2And.js2Android.JsToAndroid;


public class ActionWeb extends AppCompatActivity {
    private static final String TAG = ActionWeb.class.getSimpleName();

    private Context mContext;
    private WebView webView;
    private ProgressBar progressBar;
    private String url;
    private JsToAndroid jsToAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_web);
        init();
        findView();
        webSettings();
    }

    private void init() {
        mContext = this;
    }

    private void webSettings() {
        WebSettings settings = webView.getSettings();
        //设置页面文字的编码
        settings.setDefaultTextEncodingName("utf-8");
        //設置是否允許JavaScript注入
        settings.setJavaScriptEnabled(true);
        //是否支持缩放
        settings.setSupportZoom(true);
        //是否适应当前屏幕
        settings.setDomStorageEnabled(true);
        //设置js调用Android方法的对象极其别名
        jsToAndroid = new JsToAndroid(webView);
        webView.addJavascriptInterface(jsToAndroid, "stub");
        //設置客戶端對象
        ActionWebViewClient client = new ActionWebViewClient(jsToAndroid, progressBar);
        webView.setWebViewClient(client);
        //设置WebChromeClient对象
        ActionWebChromeClient chromeClient = new ActionWebChromeClient(mContext, progressBar);
        webView.setWebChromeClient(chromeClient);
        //加载url
        url = "file:///android_asset/index.html";
        webView.loadUrl(url);
    }

    private void findView() {
        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

}
