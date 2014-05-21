package com.coslay.asynchronoutest.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.coslay.asynchronoutest.R;
import com.coslay.asynchronoutest.lib.Base64;
import com.coslay.asynchronoutest.lib.EncryptUtil;
import com.coslay.asynchronoutest.lib.MyRestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.loopj.android.http.*;

public class MainActivity extends Activity{

    private Button btn_test1;
    private String imei;
    private Gson gson;
    private ProgressDialog pDialog;
    private String usernameString;
    private String passwordString;
    private static String TAG = "MainActivity";

      
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId();
        gson = new Gson();
        
        btn_test1 = (Button)findViewById(R.id.btn_test1);
        btn_test1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        
    }

    protected void attemptLogin() {
        if(true) {
            
        }
        usernameString = "admin";
        passwordString = "admin";  
        RequestParams requestParams= wrapParameter(usernameString,passwordString);
        try {
            pDialog = ProgressDialog.show(this, "请稍等", "数据加载中");
            attemptRest(requestParams);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private RequestParams wrapParameter(String usernameString, String passwordString) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        
        Map<String, String> mss = new LinkedHashMap<String, String>();
        mss.put("yhdm", usernameString);
        mss.put("yhkl", passwordString);
        mss.put("imei", imei);
        String value = gson.toJson(mss);   
        paramMap.put("data", Base64.encode(value.getBytes()));
        
        EncryptUtil ep = new EncryptUtil(MainActivity.this, "admin");
        String mStr = ep.getMd5(Base64.encode(value.getBytes()  ));
        paramMap.put("md5", mStr);
        
        paramMap.put("uid", "admin");
        
        paramMap.put("ios", "0");
        
        RequestParams params = new RequestParams(paramMap);    
        return params;
    }

   
    
    public void attemptRest(RequestParams requestParams) throws JSONException {
        MyRestClient.get("login.php", requestParams, new AsyncHttpResponseHandler() {
            public void onSuccess(String result) { // 获取数据成功会调用这里
                pDialog.dismiss();
                Log.e(TAG, "成功:"+result);
                String dataString = result.substring(result.indexOf("data=") + 5,
                        result.indexOf("&md5="));
                byte[] b = null;
                try {
                    b = Base64.decode(dataString);
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                String josnStr = new String(b);
                // 解析json字符串
                Map<String, String> mso = gson.fromJson(josnStr,
                        new TypeToken<Map<String, String>>() {
                        }.getType());
                if (mso.get("res").equals("1")) {
                    String resMsg = mso.get("resmsg");
                    Log.e(TAG, "登录成功");
                    // 登录成功
                } else if (mso.get("res").equals("-1")) {
                        Log.e(TAG, "登陆失败 -1:"+mso.get("resmeg"));
                    // 登陆失败
                } else if (mso.get("res").equals("500")) {
                        Log.e(TAG, "登陆失败 500:"+mso.get("resmeg"));
                    // 校验码错误处理
                }
            };
            public void onFailure(Throwable e) { // 失败，调用
                Log.e(TAG, "失败");
            };
            public void onFinish() { // 完成后调用，失败，成功，都要掉
                Log.e(TAG, "完成");
            };
        });
    }
    
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
