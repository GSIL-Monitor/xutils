package com.ckjava.xutils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtils extends EncodesUtils implements Constants {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    private static int timeout = 100000;
    private static String charset = CHARSET.UTF8;

    /**
     * put 请求, body 是 xml 字符串 或者 json 字符串
     *
     * @param url        String
     * @param headers    请求头
     * @param parameters 请求参数
     * @param bodyString Object 请求体
     * @return String 返回内容
     */
    public static String put(String url, Map<String, String> headers, Map<String, String> parameters, String bodyString) {
        CloseableHttpClient httpclient = initWeakSSLClient();
        try {
            log.info("create http put:" + url);
            // 添加请求参数
            url = appendRequestParameter(url, parameters);
            HttpPut httpPut = new HttpPut(url);
            // 添加请求头
            String contentType = appendRequestHeader(headers, httpPut);
            // 设置请求体
            addRequestBody(bodyString, httpPut, contentType);

            return invoke(httpclient, httpPut);
        } catch (Exception e) {
            log.error("http put has error", e);
            return null;
        } finally {
            IOUtils.closeQuietly(httpclient);
        }

    }

    /**
     * post 请求, body 是 xml 字符串 或者 json 字符串
     *
     * @param url        请求url
     * @param headers    请求头
     * @param parameters 请求参数
     * @param bodyString 请求体
     * @return String 返回内容
     */
    public static String post(String url, Map<String, String> headers, Map<String, String> parameters, String bodyString) {
        CloseableHttpClient httpclient = initWeakSSLClient();
        try {
            log.info("create http post:" + url);
            // 添加请求参数
            url = appendRequestParameter(url, parameters);

            HttpPost httpost = new HttpPost(url);
            // 添加请求头
            String contentType = appendRequestHeader(headers, httpost);
            // 设置请求体
            addRequestBody(bodyString, httpost, contentType);

            return invoke(httpclient, httpost);
        } catch (Exception e) {
            log.error("http post has error", e);
            return null;
        } finally {
            IOUtils.closeQuietly(httpclient);
        }
    }

    /**
     * get 请求
     *
     * @param url     请求url
     * @param headers 请求头
     * @param params  请求参数
     * @return 返回内容
     */
    public static String get(String url, Map<String, String> headers, Map<String, String> params) {
        CloseableHttpClient httpClient = initWeakSSLClient();
        try {
            // 将请求参数追加到url后面
            url = appendRequestParameter(url, params);

            log.info("create http get:" + url);
            HttpGet httpGet = new HttpGet(url);

            // 添加请求头
            appendRequestHeader(headers, httpGet);

            return invoke(httpClient, httpGet);
        } catch (Exception e) {
            log.error("http get has error", e);
            return null;
        } finally {
            IOUtils.closeQuietly(httpClient);
        }
    }

    /**
     * delete 请求
     *
     * @param url     请求url
     * @param headers 请求头
     * @param params  请求参数
     * @return 返回内容
     */
    public static String delete(String url, Map<String, String> headers, Map<String, String> params) {
        CloseableHttpClient httpClient = initWeakSSLClient();
        try {
            // 将请求参数追加到url后面
            url = appendRequestParameter(url, params);

            log.info("create http delete:" + url);
            HttpDelete httpDelete = new HttpDelete(url);

            // 添加请求头
            appendRequestHeader(headers, httpDelete);

            return invoke(httpClient, httpDelete);
        } catch (Exception e) {
            log.error("http delete has error", e);
            return null;
        } finally {
            IOUtils.closeQuietly(httpClient);
        }
    }

    private static void addRequestBody(String bodyString, HttpEntityEnclosingRequestBase http, String contentType) {
        String[] supportTypes = new String[]{MIMETYPE.TEXT_XML, MIMETYPE.APPLICATION_JSON};
        if (StringUtils.isNotBlank(contentType)) {
            if (contentType.contains(supportTypes[0])) {
                addXmlBody(http, bodyString);
            } else if (contentType.contains(supportTypes[1])) {
                addJSONBody(http, bodyString);
            }
        }
    }

    private static String appendRequestHeader(Map<String, String> headers, AbstractHttpMessage httpMessage) {
        String contentType = null;
        if (headers != null && !headers.isEmpty()) {
            for (Iterator<Entry<String, String>> it = headers.entrySet().iterator(); it.hasNext();) {
                Entry<String, String> data = it.next();
                String key = data.getKey();
                String value = data.getValue();
                if (key.equalsIgnoreCase("Content-Type")) {
                    contentType = value;
                }
                httpMessage.addHeader(key, value);
            }
        }
        return contentType;
    }

    /**
     * 对于 String 类型的 url，这里传入的是 值 不是一个引用，所以需要返回追加好的 String
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 返回内容
     * @throws UnsupportedEncodingException 异常
     */
    private static String appendRequestParameter(String url, Map<String, String> params) throws UnsupportedEncodingException {
        if (StringUtils.isNotBlank(url) && params != null && !params.isEmpty()) {
            boolean flag = true;
            for (Iterator<Entry<String, String>> it = params.entrySet().iterator(); it.hasNext(); ) {
                Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (flag && url.indexOf("?") == -1) {
                    url += "?" + urlEncode(key) + "=" + urlEncode(value);
                    flag = false;
                } else {
                    url += "&" + urlEncode(key) + "=" + urlEncode(value);
                }
            }
        }
        return url;
    }

    private static String invoke(CloseableHttpClient httpclient, HttpUriRequest httprequest) {
        HttpResponse response = sendRequest(httpclient, httprequest);
        return paseResponse(response);
    }

    private static String paseResponse(HttpResponse response) {
        log.info("get response from http server..");
        HttpEntity entity = response.getEntity();

        // 获取 contentType
        String contentType = "";
        Header[] headers = response.getAllHeaders();
        for (int i = 0, c = ArrayUtils.getSize(headers); i < c; i++) {
            Header header = ArrayUtils.getValue(headers, i);
            String headerName = header.getName();
            if (headerName.equalsIgnoreCase("Content-Type")) {
                contentType = "application/octet-stream";
            }
        }

        if (contentType.equalsIgnoreCase("application/octet-stream")) {
            Header contentEncodingHeader = entity.getContentEncoding();
            if (contentEncodingHeader == null || StringUtils.isBlank(contentEncodingHeader.getValue())) {
                Locale locale = response.getLocale();
                if (locale != null && locale.getLanguage().equals("zh")) {
                    charset = CHARSET.GB2312;
                }
            } else {
                charset = contentEncodingHeader.getValue();
            }
        }

        log.info("response status: " + response.getStatusLine());
        try {
            return EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            log.error("paseResponse has error", e);
            return null;
        }
    }

    private static HttpResponse sendRequest(HttpClient httpclient, HttpUriRequest request) {
        log.info("execute request...");
        HttpResponse response = null;

        try {
            response = httpclient.execute(request);
        } catch (Exception e) {
            log.error("sendRequest has error", e);
        }

        return response;
    }

    /**
     * 设置JSON请求体
     *
     * @param http
     * @param bodyString
     */
    private static void addJSONBody(HttpEntityEnclosingRequestBase http, String bodyString) {
        try {
            StringEntity se = new StringEntity(bodyString, ContentType.create(MIMETYPE.APPLICATION_JSON, charset));
            http.setEntity(se);
        } catch (Exception e) {
            log.error("addJSONBody has Exception", e);
        }
    }


    /**
     * 设置XML请求体
     *
     * @param http
     * @param obj
     */
    private static void addXmlBody(HttpEntityEnclosingRequestBase http, Object obj) {
        try {
            StringEntity se = new StringEntity(StringUtils.getStr(obj), ContentType.create(MIMETYPE.TEXT_XML, charset));
            http.setEntity(se);
        } catch (Exception e) {
            log.error("addXmlBody has Exception", e);
        }
    }

    private static CloseableHttpClient initWeakSSLClient() {
        HttpClientBuilder b = HttpClientBuilder.create();

        // setup a Trust Strategy that allows all certificates.
        //
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            // do nothing, has been handled outside
        }
        b.setSslcontext(sslContext);

        // don't check Hostnames, either.
        //      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
        X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

        // here's the special part:
        //      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
        //      -- and create a Registry, to register it.
        //
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        // now, we create connection-manager using our Registry.
        //      -- allows multi-threaded use
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        b.setConnectionManager(connMgr);

        /**
         * Set timeout option
         */
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(timeout);
        configBuilder.setSocketTimeout(timeout);
        b.setDefaultRequestConfig(configBuilder.build());

        // finally, build the HttpClient;
        //      -- done!
        CloseableHttpClient sslClient = b.build();

        return sslClient;
    }

    /**
     * 向url添加请求参数的键值对
     *
     * @param url       请求url
     * @param paramname 参数名称
     * @param value     参数值
     * @throws UnsupportedEncodingException 未知编码异常
     */
    public static String addParamValue(String url, String paramname, String value) throws UnsupportedEncodingException {
        if (paramname == null || value == null) {
            return StringUtils.EMPTY;
        }
        String tempUrl = url;
        tempUrl += urlEncode(paramname) + "=" + urlEncode(value) + "&";
        if (isValidUrl(tempUrl)) {
            return tempUrl;
        } else {
            return url;
        }
    }

    /**
     * 拼接url时候判断是否合法
     *
     * @param urlString http url
     * @return 是否为合法的 Url
     */
    public static boolean isValidUrl(String urlString) {
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            return false;
        }
        if (uri.getHost() == null) {
            return false;
        }
        if (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https")) {
            return true;
        }
        return false;
    }

}
