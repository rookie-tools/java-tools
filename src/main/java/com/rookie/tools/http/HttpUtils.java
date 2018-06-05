/**
 * @(#)HttpUtils.java, 2018-06-04.
 * <p>
 * Copyright 2018 Stalary.
 */
package com.rookie.tools.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpUtils
 *http操作的工具类
 * @author lirongqian
 * @since 2018/06/04
 */
@Slf4j
public class HttpUtils {

    private static final String UNKNOWN = "unknown";

    private static final String COUNTRY = "country";

    private static final String PROVINCE = "province";

    private static final String CITY = "city";

    /**
     * 通过新浪的api获取ip对应的地址
     */
    private static final String ADDRESS_API = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

    /**
     * get请求
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse ret = HttpClient.get(httpGet)) {
            int statusCode = ret.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.warn("request failed with code:" + statusCode);
                return null;
            }
            HttpEntity entity = ret.getEntity();
            String responseStr = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            log.info("httpJsonGet: " + url + "; " + responseStr);
            return responseStr;
        } catch (Exception e) {
            log.warn("httpJsonGet failure!", e);
            return null;
        }
    }

    /**
     * post请求
     * @param url
     * @param data
     * @return
     */
    public static String httpPost(String url, Object data) {
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(data), Charsets.UTF_8);
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("Content-Type", "application/json");
            try (CloseableHttpResponse response = HttpClient.post(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    log.warn("post fail with code:" + statusCode);
                }
                HttpEntity entity = response.getEntity();
                String responseStr = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                log.info("httpJsonPost: " + url + "; " + responseStr);
                return responseStr;
            } catch (Exception e) {
                log.warn("httpJsonPost fail!", e);
            }
        } catch (Exception e) {
            log.warn("httpJsonPost Exception!", e);
        }
        return null;
    }

    /**
     * 获取request请求的ip
     * @param request
     * @return
     */
    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后可能有多个ip值，返回第一个真是ip
            int index = ip.indexOf(",");
            if (index != -1) {
                ip = ip.substring(0, index);
            }
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isEmpty(ip)) {
            log.warn("get request ip error! " + request.getPathInfo());
        }
        return ip;
    }

    /**
     * 获取地址，格式为-》国家:省份:城市
     * @return
     */
    public static String getAddress(HttpServletRequest request) {
        String ip = getIp(request);
        if (StringUtils.isEmpty(ip)) {
            return null;
        }
        String str = httpGet(ADDRESS_API + ip);
        if (str == null) {
            log.warn("get address error! " + request.getPathInfo());
            return null;
        }
        // 当不包含country即非正常返回
        if (!str.contains(COUNTRY)) {
            log.warn("get address error! " + request.getPathInfo());
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.getString(COUNTRY) +
                ":" +
                jsonObject.getString(PROVINCE) +
                ":" +
                jsonObject.getString(CITY);
    }

}