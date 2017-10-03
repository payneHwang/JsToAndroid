package com.js2And.js2Android;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * Created by HUANG JIN on 2017/10/3.
 * Android与JS接口互相调用实现类
 */

public class JsToAndroid {
    private static final String TAG = JsToAndroid.class.getSimpleName();
    private WebView webView;

    public JsToAndroid(WebView webView) {
        this.webView = webView;
    }

    //Android调用Js的接口，想Js传送数据
    @JavascriptInterface
    public void sendParams() {
        webView.loadUrl("javascript:sendParams()");
    }

    //提供Js调用的方法
    @JavascriptInterface
    public void getParams(String s) {
        Log.e(TAG, "Js开始调用Android的接口，向Andrioid传递参数..." + s);
    }

}