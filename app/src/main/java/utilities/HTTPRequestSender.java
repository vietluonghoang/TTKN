package utilities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.truytimkhachno.ttkn.MainActivity;
import com.truytimkhachno.ttkn.Splash;

import java.util.Map;

/**
 * Created by VietLH on 10/30/2016.
 */

public class HTTPRequestSender {
    private RequestQueue queue;
    private String targetUrl;
    private Map<String, String> params;

    private String responseValue;
    private String errorMessage;

    public HTTPRequestSender(Context context, String targetUrl, Map<String, String> params) {
        queue = Volley.newRequestQueue(context);
        this.targetUrl = targetUrl;
        this.params = params;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void doGet() {
        doRequest(Request.Method.GET);
    }

    public void doPost() {
        doRequest(Request.Method.POST);
    }

    private void doRequest(int method) {
        StringRequest postRequest = new StringRequest(method, targetUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseValue = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorMessage = error.getMessage();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        queue.add(postRequest);
    }
}
