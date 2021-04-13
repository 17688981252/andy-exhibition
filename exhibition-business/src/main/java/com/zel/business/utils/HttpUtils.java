//package com.zel.business.utils;
//
//import cn.hutool.http.body.RequestBody;
//import com.google.common.net.MediaType;
//import org.apache.catalina.connector.Request;
//import org.apache.catalina.connector.Response;
//import org.apache.http.message.BasicNameValuePair;
//import org.omg.CORBA.NameValuePair;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//public class HttpUtils {
//
//    /**
//     * okHttp3的 POST 请求 , 请求参数格式为 key-value
//     * @param url 请求url
//     * @param params 请求参数 , Map
//     * @return
//     * @author zfan
//     * @email zfanxyz@gmail.com
//     */
//    public static String okHttpPostString(String url, Map<String, Object> params) {
//
//        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//
//        Collection<String> keyset = params.keySet();
//        List<String> keylist = new ArrayList<String>(keyset);
//
//        Collections.sort(keylist);
//        for (int i = 0; i < keylist.size(); i++) {
//            if (keylist.get(i) != null && !"".equals(params.get(keylist.get(i)))) {
//                paramList.add(new BasicNameValuePair(keylist.get(i), params.get(keylist.get(i)).toString()));
//            }
//        }
//
//        //请求参数
//        String requestString = URLEncodedUtils.format(paramList, Charset.forName("UTF-8"));
//
//        //创建一个默认的OkHttpClient实例：
//        OkHttpClient client = new OkHttpClient.Builder()
//                .readTimeout(100, TimeUnit.SECONDS)  //设置读取的超时时间 s
//                .writeTimeout(60, TimeUnit.SECONDS)   //设置写的超时时间 s
//                .connectTimeout(60, TimeUnit.SECONDS) //设置连接的超时时间 s
//                .build();
//
//        //post 的 key-value 形式的请求参数的格式
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded"); //数据类型为 key-value 格式
//
//        //创建一个实体 RequestBody
//        RequestBody requestBody = RequestBody.create(mediaType, requestString);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            if(response.isSuccessful()){
//                return  response.body().string();
//            }else {
//                throw new IOException("Unexpected code : " + response);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }
//
//
//
//}
