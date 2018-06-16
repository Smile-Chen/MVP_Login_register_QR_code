package com.example.hp.mn2.http;

import android.os.Handler;
import android.os.Looper;
import android.system.ErrnoException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hp on 2018/6/15.
 */

public class HttpUtils {
    private final Handler handler;
    private OkHttpClient mokHttpClient;
    private static HttpUtils httpUtils=new HttpUtils();

    private HttpUtils(){
        handler = new Handler(Looper.getMainLooper());
        mokHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .build();
    }

    public static HttpUtils getHttpUtils() {
           if (httpUtils==null){
               synchronized (HttpUtils.class){
                   if (httpUtils==null){
                       return httpUtils=new HttpUtils();
                   }
               }
           }
        return httpUtils;
    }
    //封装get
    public void get(String url, final OKHttpCallChe okHttpCallChe){
        final Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call=mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(okHttpCallChe != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallChe.getError(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(okHttpCallChe != null){
                    if (response != null && response.isSuccessful()){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String json = response.body().string();
                                    okHttpCallChe.getSuccess(json);
                                    return;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
    //封装post
    public void get(String url, Map<String,String>map, final OKHttpCallChe okHttpCallChe){
        FormBody.Builder formbody = new FormBody.Builder();
          for (String key:map.keySet()){
              formbody.add(key,map.get(key));
          }
        FormBody build = formbody.build();
        Request request = new Request.Builder()//发送请求
                .post(build)
                .url(url)
                .build();
        Call call = mokHttpClient.newCall(request);
           call.enqueue(new Callback() {
               @Override
               public void onFailure(Call call, final IOException e) {
                  if (okHttpCallChe!=null){
                      handler.post(new Runnable() {
                          @Override
                          public void run() {
                              okHttpCallChe.getError(e);
                          }
                      });
                  }
               }
               @Override
               public void onResponse(Call call, final Response response) throws IOException {
                     if (okHttpCallChe!=null){
                         if (response!=null&&response.isSuccessful()){
                             handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     try {
                                         String json = response.body().string();
                                         okHttpCallChe.getSuccess(json);
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     }
                                 }
                             });
                         }
                     }

               }
           });
    }


    public interface OKHttpCallChe{
        void getSuccess(String json);
        void getError(Exception error);
    }



}
