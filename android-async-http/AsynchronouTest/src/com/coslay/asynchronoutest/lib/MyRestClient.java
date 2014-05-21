package com.coslay.asynchronoutest.lib;

import com.loopj.android.http.*;

public class MyRestClient {
    private static final String BASE_URL = "http://10.60.49.240/zcgl/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();// 实例话对象
    static {
        client.setTimeout(11000); // 设置链接超时，如果不设置，默认为10s
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static AsyncHttpClient getClient() {
        return client;
    }

    public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
    {
        client.get(getAbsoluteUrl(urlString), res);
    }

    public static void get(String urlString, RequestParams params,
            AsyncHttpResponseHandler res) // url里面带参数
    {
        client.get(getAbsoluteUrl(urlString), params, res);
    }

    public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
    {
        client.get(getAbsoluteUrl(urlString), res);
    }

    public static void get(String urlString, RequestParams params,
            JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
    {
        client.get(getAbsoluteUrl(urlString), params, res);
    }

    public static void get(String urlString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        client.get(getAbsoluteUrl(urlString), bHandler);
    }

    public static void post(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
    {
        client.post(getAbsoluteUrl(urlString), res);
    }

    public static void post(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
    {
        client.post(getAbsoluteUrl(urlString), res);
    }

    public static void post(String urlString, RequestParams params,
            JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
    {
        client.post(getAbsoluteUrl(urlString), params, res);
    }

    public static void post(String urlString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        client.post(getAbsoluteUrl(urlString), bHandler);
    }
}
