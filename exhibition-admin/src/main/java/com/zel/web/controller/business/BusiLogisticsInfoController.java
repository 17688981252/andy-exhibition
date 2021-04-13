package com.zel.web.controller.business;

import com.alibaba.fastjson.JSONObject;
import com.zel.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*expressCategory:快递单号所对应的快递公司的编码，
        比如 “yuantong”
        number:要查询的快递单号
        customer:快递100分配的客户编码
        key：授权的 key
        sign:签名 param+key+customer，这几个必须有*/

@Controller
public class BusiLogisticsInfoController extends BaseController {


    /**
     * 调用快递100接口，查询物流信息
     *
     * @param number          要查询的快递单号
     * @param expressCategory 快递单号所对应的快递公司的编码，比如 “yuantong”
     * @return
     */
    public List<Map<String, Object>> rightKuaidi100(String number, String expressCategory) {
        //此处根据快递单号拿到对应的公司的编码，比如 “yuantong”,这部分代码我就不上了
        //String expressCategory = kuaidi100Service.getExpressCategory(number);

//        String param = "com"+":" + expressCategory + "num"+":" + number;
        String param ="{\"com\":+ expressCategory,\"num\":+ number}";//com 快递公司标识，num:快递单号
        String customer = "88C6CA**************B65AE2FO1";
        String key = "rt*******66";
        String sign = signMD5(param + key + customer);
        HashMap params = new HashMap();
        params.put("param", param);
        params.put("sign", sign.toUpperCase());
        //params.put("sign",sign);
        params.put("customer", customer);

//        String responseString = HttpUtils.okHttpPostString("http://poll.kuaidi100.com/poll/query.do", params);
//        //格式化返回参数信息
//        Map<String, Object> map1 = (Map<String, Object>) JSONObject.parse(responseString);
//
//        return datas = (List<Map<String, Object>>) map1.get("data");
        return null;

    }

    /**
     * 快递100 签名
     *
     * @param s
     * @return
     */
    public static String signMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return bytesToHex(bytes);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);

        for (int i = 0; i < bytes.length; ++i) {
            ret.append(HEX_DIGITS[bytes[i] >> 4 & 15]);
            ret.append(HEX_DIGITS[bytes[i] & 15]);
        }

        return ret.toString();
    }





}
