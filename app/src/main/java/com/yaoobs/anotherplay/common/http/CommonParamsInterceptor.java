package com.yaoobs.anotherplay.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yaoobs.anotherplay.common.Constant;
import com.yaoobs.anotherplay.common.util.DensityUtil;
import com.yaoobs.anotherplay.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context, Gson gson) {

        this.mContext = context;
        this.mGson = gson;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        try {
            String method = request.method();

            HashMap<String, Object> commomParamsMap = new HashMap<>();
            commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commomParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
            commomParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
            commomParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
            commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
            commomParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
            commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");

            if (method.equals("GET")) {
                HttpUrl httpUrl = request.url();
                HashMap<String, Object> rootMap = new HashMap<>();
                Set<String> paramNames = httpUrl.queryParameterNames();
                for (String key : paramNames) {
                    if (Constant.PARAM.equals(key)) {
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        if (oldParamJson != null) {
                            HashMap<String, Object> p = mGson.fromJson(oldParamJson, HashMap.class); // 原始参数
                            if (p != null) {
                                for (Map.Entry<String, Object> entry : p.entrySet()) {

                                    rootMap.put(entry.getKey(), entry.getValue());
                                }
                            }
                        } else {
                            rootMap.put(key, httpUrl.queryParameter(key));
                        }
                    }
                }


                rootMap.put("publicParams", commomParamsMap);
                String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}

                String url = httpUrl.toString();
                int index = url.indexOf("?");
                if (index > 0) {
                    url = url.substring(0, index);
                }

                url = url + "?" + Constant.PARAM + "=" + newJsonParams; //  http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}


                request = request.newBuilder().url(url).build();
            } else if (method.equals("POST")) {

                RequestBody body = request.body();

                HashMap<String, Object> rootMap = new HashMap<>();

                if (body instanceof FormBody) { //form 表单
                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }
                } else {

                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParams = buffer.readUtf8();
                    rootMap = mGson.fromJson(oldJsonParams, HashMap.class); // 原始参数
                    rootMap.put("publicParams", commomParamsMap);
                    String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}

                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return chain.proceed(request);
    }
}
