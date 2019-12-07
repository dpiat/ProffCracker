package com.sinhro.mentorapp.API;

import android.content.Context;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import java.util.function.Consumer;

public class WebViewClient extends android.webkit.WebViewClient {
    public String accessToken=null;
    Context context;
    Consumer<String> onKeyLoad;
    public WebViewClient(Context context, Consumer<String> onKeyLoad) {
        this.context=context;
        this.onKeyLoad = onKeyLoad;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        System.out.println("daya:"+request.getUrl().toString());
        if(request.getUrl().toString().contains("access_token")){
       System.out.println("pomogite:"+request.getUrl().toString());
       String rq=request.getUrl().toString();
       int i1=rq.indexOf("access_token=")+13;
       int i2=rq.indexOf("&expires_in");
        this.accessToken=request.getUrl().toString().substring(i1,i2);
        onKeyLoad.accept(this.accessToken);
        view.loadUrl(request.getUrl().toString());
        return  true;
        }
      return false;
    }


}
