package com.js2And.client;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by HUANG JIN on 2017/10/3.
 */

public class ActionWebChromeClient extends WebChromeClient {
    private static final String TAG = ActionWebChromeClient.class.getSimpleName();
    private Context mContext;
    private AlertDialog dialog;
    private ProgressBar progressBar;

    public ActionWebChromeClient(Context mContext, ProgressBar progressBar) {
        this.mContext = mContext;
        this.progressBar = progressBar;
    }


    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Log.e(TAG, "onProgressChanged---newProgress---" + newProgress);
        progressBar.setProgress(newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        Log.e(TAG, "onReceivedTitle---title---" + title);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.e(TAG, "onJsAlert---message---" + message);
        //用Android的弹窗方式拦截Js内的弹窗
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Js Alert");
        builder.setMessage(message);
        builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        dialog = builder.create();
        dialog.show();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        Log.e(TAG, "onJsConfirm---message---" + message);
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Log.e(TAG, "onJsPrompt---message---" + message);
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
