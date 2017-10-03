package com.js2And.client;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.js2And.js2Android.JsToAndroid;

/**
 * Created by HUANG JIN on 2017/10/3.
 * WebViewClient客户端对象
 */

public class ActionWebViewClient extends WebViewClient {
    private static final String TAG = ActionWebViewClient.class.getSimpleName();

    private JsToAndroid jsToAndroid;
    private ProgressBar progress;


    public ActionWebViewClient(JsToAndroid jsToAndroid, ProgressBar progress) {
        this.jsToAndroid = jsToAndroid;
        this.progress = progress;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e(TAG, "shouldOverridingUrl----" + url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.e(TAG, "shouldOverridingUrl----" + url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        progress.setVisibility(View.GONE);
        Log.e(TAG, "onPageFinished----" + url + "---开始调用Js的方法：---sendParams()");
        jsToAndroid.sendParams();
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        Log.e(TAG, "onLoadResource----" + url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        view.stopLoading();
        Log.e(TAG, "onReceivedError----" + error);
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
    }
}
