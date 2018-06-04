/**
 * @(#)HttpConfig.java, 2018-06-04.
 * <p>
 * Copyright 2018 Stalary.
 */
package com.rookie.tools.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;

/**
 * HttpClient
 *
 * @author lirongqian
 * @since 2018/06/04
 */
@Slf4j
public class HttpClient {

    private static final CloseableHttpClient client;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        RequestConfig requestConfig = RequestConfig.custom()
                // 创建连接的超时时间
                .setConnectTimeout(3000)
                // 从connection manager中获取连接的最大等待时间
                .setConnectionRequestTimeout(3000)
                // 请求的超时时间
                .setSocketTimeout(3000)
                .build();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(100);
        client = HttpClients
                .custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    public static String get(String url) {
        try {
            Content content = Request.Get(url)
                    .connectTimeout(2000)
                    .socketTimeout(2000)
                    .execute().returnContent();
            return IOUtils.toString(content.asStream(), "UTF-8");
        } catch (Exception e) {
            log.warn("Http Get Exception", e);
        }
        return null;
    }

    public static CloseableHttpResponse post(HttpPost httpPost) throws IOException {
        return client.execute(httpPost);
    }

    public static CloseableHttpResponse get(HttpGet httpGet) throws IOException {
        return client.execute(httpGet);
    }
}