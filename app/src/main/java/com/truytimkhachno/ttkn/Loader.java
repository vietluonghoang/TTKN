package com.truytimkhachno.ttkn;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import entities.LoaderAttributes;
import utilities.HTTPRequestSender;

public class Loader extends AppCompatActivity {

    public static final int POST_METHOD = Request.Method.POST;
    public static final int GET_METHOD = Request.Method.GET;
    public static final String RESPONSE = "response";
    public static final String ERROR_MESSAGE = "errorMessage";

    private Context context;
    private String targetUrl;
    private Map<String, String> params;
    private int method;
    private Object targetClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        initAttribute();
        new PrefetchData().execute();
    }

    private void initAttribute(){
        this.context = this.getApplicationContext();
        this.targetUrl = LoaderAttributes.getTargetUrl();
        this.params=LoaderAttributes.getParams();
        this.method = LoaderAttributes.getMethod();
        this.targetClass = LoaderAttributes.getTargetClass();
    }

    private class PrefetchData extends AsyncTask<Void, Void, Void> {
        private HTTPRequestSender sender;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
            sender = new HTTPRequestSender(context,targetUrl,params);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            if(method==GET_METHOD){
                sender.doGet();
            }else if(method==POST_METHOD){
                sender.doPost();
            }

            int timeout=30;
            while(sender.getResponseValue()==null&&sender.getErrorMessage()==null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeout--;
                if(timeout<1){
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and lauch main activity
            Intent i = new Intent(Loader.this, ClientList.class);
            i.putExtra(RESPONSE, sender.getResponseValue());
            i.putExtra(ERROR_MESSAGE, sender.getErrorMessage());
            startActivity(i);

            // close this activity
            finish();
        }

    }

}
