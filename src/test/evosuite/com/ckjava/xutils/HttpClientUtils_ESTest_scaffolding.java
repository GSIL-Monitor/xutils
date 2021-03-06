/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sat Dec 15 10:33:32 GMT 2018
 */

package com.ckjava.xutils;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class HttpClientUtils_ESTest_scaffolding {

  @org.junit.Rule 
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass 
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "com.ckjava.xutils.HttpClientUtils"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    try { initMocksToAvoidTimeoutsInTheTests(); } catch(ClassNotFoundException e) {} 
  } 

  @AfterClass 
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before 
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After 
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("file.encoding", "GBK"); 
    java.lang.System.setProperty("java.awt.headless", "true"); 
    java.lang.System.setProperty("java.io.tmpdir", "C:\\Users\\ck\\AppData\\Local\\Temp\\"); 
    java.lang.System.setProperty("user.country", "CN"); 
    java.lang.System.setProperty("user.dir", "D:\\workspace\\git-workspace\\play\\xutils"); 
    java.lang.System.setProperty("user.home", "C:\\Users\\ck"); 
    java.lang.System.setProperty("user.language", "zh"); 
    java.lang.System.setProperty("user.name", "ck"); 
    java.lang.System.setProperty("user.timezone", "Asia/Shanghai"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(HttpClientUtils_ESTest_scaffolding.class.getClassLoader() ,
      "org.apache.http.io.HttpMessageParserFactory",
      "org.apache.http.impl.conn.DefaultHttpResponseParserFactory",
      "org.apache.http.impl.execchain.RequestAbortedException",
      "org.apache.commons.lang3.StringUtils",
      "org.apache.http.impl.execchain.ProtocolExec",
      "org.apache.http.config.Registry",
      "org.apache.http.cookie.MalformedCookieException",
      "org.apache.http.impl.conn.SystemDefaultDnsResolver",
      "org.apache.http.client.protocol.RequestClientConnControl",
      "org.apache.http.conn.ConnectionRequest",
      "org.apache.http.impl.client.DefaultUserTokenHandler",
      "org.apache.http.impl.conn.DefaultManagedHttpClientConnection",
      "org.apache.commons.io.output.ByteArrayOutputStream",
      "org.apache.http.conn.HttpClientConnectionManager",
      "org.apache.http.client.protocol.RequestAcceptEncoding",
      "org.apache.http.HttpException",
      "org.apache.http.cookie.CookieSpec",
      "org.apache.http.impl.cookie.RFC2965SpecFactory",
      "org.apache.http.pool.AbstractConnPool$1",
      "org.apache.http.conn.ssl.AllowAllHostnameVerifier",
      "org.apache.http.client.CredentialsProvider",
      "org.apache.http.client.ClientProtocolException",
      "org.apache.http.pool.RouteSpecificPool",
      "org.apache.http.client.methods.Configurable",
      "org.apache.http.config.RegistryBuilder",
      "org.apache.http.params.AbstractHttpParams",
      "org.apache.http.conn.ssl.AbstractVerifier",
      "org.apache.http.conn.ssl.SSLContextBuilder$TrustManagerDelegate",
      "org.apache.http.auth.Credentials",
      "org.apache.http.io.HttpMessageParser",
      "org.apache.http.client.methods.AbstractExecutionAwareRequest",
      "org.apache.http.impl.BHttpConnectionBase",
      "org.apache.http.io.HttpMessageWriter",
      "org.apache.http.impl.execchain.RedirectExec",
      "org.apache.http.HttpClientConnection",
      "org.apache.http.conn.ConnectionPoolTimeoutException",
      "org.apache.http.conn.routing.HttpRouteDirector",
      "org.apache.http.pool.ConnPool",
      "org.apache.http.protocol.HttpProcessor",
      "org.apache.http.auth.AuthProtocolState",
      "org.apache.http.client.RedirectStrategy",
      "org.apache.http.impl.client.BasicCookieStore",
      "org.apache.http.conn.routing.BasicRouteDirector",
      "org.apache.http.protocol.HttpContext",
      "org.apache.http.params.HttpParams",
      "org.apache.http.client.NonRepeatableRequestException",
      "org.apache.http.HttpResponse",
      "org.apache.http.impl.client.AuthenticationStrategyImpl",
      "org.apache.http.impl.client.HttpClientBuilder",
      "org.apache.http.message.HeaderGroup",
      "org.apache.http.impl.io.DefaultHttpRequestWriterFactory",
      "org.apache.http.client.protocol.RequestAuthCache",
      "org.apache.http.impl.conn.PoolingHttpClientConnectionManager$InternalConnectionFactory",
      "org.apache.http.impl.conn.DefaultSchemePortResolver",
      "org.apache.http.Header",
      "org.apache.http.conn.HttpHostConnectException",
      "org.apache.http.impl.NoConnectionReuseStrategy",
      "org.apache.http.impl.client.BasicCredentialsProvider",
      "org.apache.http.conn.ConnectionKeepAliveStrategy",
      "org.apache.http.cookie.CookieSpecFactory",
      "org.apache.http.conn.ssl.X509HostnameVerifier",
      "org.apache.http.protocol.ChainBuilder",
      "org.apache.http.impl.client.DefaultHttpRequestRetryHandler",
      "org.apache.http.impl.conn.PoolingHttpClientConnectionManager",
      "org.apache.http.impl.client.DefaultRedirectStrategy",
      "org.apache.http.impl.auth.KerberosSchemeFactory",
      "org.apache.http.client.methods.HttpRequestBase",
      "org.apache.http.HttpEntity",
      "org.apache.http.pool.PoolEntryCallback",
      "org.apache.http.impl.DefaultConnectionReuseStrategy",
      "org.apache.http.conn.ssl.SSLContextBuilder",
      "org.apache.http.pool.ConnFactory",
      "org.apache.http.client.methods.HttpGet",
      "org.apache.http.protocol.BasicHttpContext",
      "com.ckjava.xutils.Constants",
      "org.apache.http.client.CircularRedirectException",
      "org.apache.http.impl.execchain.ClientExecChain",
      "org.apache.commons.logging.impl.Jdk14Logger",
      "org.apache.http.HttpVersion",
      "org.apache.http.conn.SchemePortResolver",
      "org.apache.http.conn.DnsResolver",
      "org.apache.http.impl.client.TargetAuthenticationStrategy",
      "org.apache.http.params.CoreProtocolPNames",
      "org.apache.http.auth.AuthScheme",
      "org.apache.http.message.AbstractHttpMessage",
      "com.ckjava.xutils.StringUtils",
      "org.apache.http.auth.MalformedChallengeException",
      "org.apache.http.HttpEntityEnclosingRequest",
      "org.apache.http.ReasonPhraseCatalog",
      "org.apache.http.impl.cookie.BrowserCompatSpecFactory$SecurityLevel",
      "org.apache.http.client.UserTokenHandler",
      "org.apache.http.impl.auth.DigestSchemeFactory",
      "org.apache.http.impl.conn.HttpClientConnectionOperator",
      "org.apache.http.HttpResponseFactory",
      "org.apache.http.client.methods.HttpPut",
      "org.apache.http.ConnectionReuseStrategy",
      "org.apache.http.client.protocol.RequestDefaultHeaders",
      "org.apache.http.message.BasicHeader",
      "org.apache.http.impl.conn.ConnectionShutdownException",
      "com.ckjava.xutils.EncodesUtils",
      "org.apache.http.conn.ManagedHttpClientConnection",
      "org.apache.http.client.protocol.ResponseContentEncoding",
      "org.apache.http.message.BasicLineParser",
      "org.apache.http.client.methods.HttpPost",
      "org.apache.http.auth.AuthSchemeProvider",
      "org.apache.http.util.Asserts",
      "org.apache.http.client.config.RequestConfig",
      "org.apache.http.StatusLine",
      "com.ckjava.xutils.HttpClientUtils$1",
      "org.apache.http.impl.DefaultBHttpClientConnection",
      "org.apache.http.impl.DefaultHttpResponseFactory",
      "org.apache.http.RequestLine",
      "org.apache.http.conn.HttpConnectionFactory",
      "org.apache.http.protocol.RequestContent",
      "org.apache.http.cookie.CookieIdentityComparator",
      "org.apache.http.config.Lookup",
      "org.apache.http.HttpMessage",
      "org.apache.http.impl.cookie.NetscapeDraftSpecFactory",
      "org.apache.http.HttpRequestInterceptor",
      "org.apache.http.HeaderElementIterator",
      "org.apache.http.client.AuthCache",
      "org.apache.http.pool.AbstractConnPool",
      "org.apache.http.HeaderIterator",
      "org.apache.commons.codec.DecoderException",
      "org.apache.http.conn.ClientConnectionManager",
      "org.apache.http.HttpInetConnection",
      "org.apache.http.message.LineFormatter",
      "org.apache.http.cookie.CookieSpecProvider",
      "org.apache.http.HttpRequest",
      "org.apache.http.pool.ConnPoolControl",
      "org.apache.commons.io.IOUtils",
      "org.apache.http.client.AuthenticationStrategy",
      "org.apache.http.conn.socket.ConnectionSocketFactory",
      "org.apache.http.protocol.RequestTargetHost",
      "org.apache.http.pool.PoolEntry",
      "org.apache.http.message.BasicLineFormatter",
      "org.apache.http.client.RedirectException",
      "org.apache.http.client.methods.HttpUriRequest",
      "org.apache.http.protocol.HttpRequestExecutor",
      "org.apache.http.client.methods.HttpRequestWrapper",
      "org.apache.http.impl.cookie.IgnoreSpecFactory",
      "org.apache.http.impl.auth.HttpAuthenticator",
      "org.apache.http.impl.conn.ManagedHttpClientConnectionFactory",
      "org.apache.http.conn.ConnectTimeoutException",
      "org.apache.http.client.methods.AbortableHttpRequest",
      "org.apache.http.client.HttpClient",
      "org.apache.commons.io.output.StringBuilderWriter",
      "org.apache.http.auth.AuthSchemeFactory",
      "org.apache.http.protocol.ImmutableHttpProcessor",
      "org.apache.http.impl.auth.SPNegoSchemeFactory",
      "com.ckjava.xutils.ExceptionUtils",
      "org.apache.http.impl.conn.PoolingHttpClientConnectionManager$ConfigData",
      "com.ckjava.xutils.IOUtils",
      "org.apache.http.TokenIterator",
      "org.apache.http.client.methods.HttpRequestWrapper$HttpEntityEnclosingRequestWrapper",
      "org.apache.http.protocol.HttpCoreContext",
      "org.apache.http.impl.conn.CPool",
      "org.apache.http.impl.auth.NTLMSchemeFactory",
      "org.apache.http.ProtocolVersion",
      "org.apache.http.client.protocol.RequestExpectContinue",
      "org.apache.http.util.VersionInfo",
      "org.apache.http.impl.cookie.RFC2109SpecFactory",
      "com.ckjava.xutils.HttpClientUtils",
      "org.apache.http.conn.UnsupportedSchemeException",
      "org.apache.http.ProtocolException",
      "org.apache.http.impl.cookie.BrowserCompatSpecFactory",
      "org.apache.http.client.methods.HttpEntityEnclosingRequestBase",
      "org.apache.http.params.BasicHttpParams",
      "org.apache.http.client.protocol.HttpClientContext",
      "org.apache.http.impl.client.ProxyAuthenticationStrategy",
      "org.apache.http.conn.ssl.StrictHostnameVerifier",
      "org.apache.http.io.HttpMessageWriterFactory",
      "org.apache.http.concurrent.Cancellable",
      "org.apache.http.impl.execchain.MainClientExec",
      "org.apache.http.protocol.HttpProcessorBuilder",
      "org.apache.http.impl.execchain.TunnelRefusedException",
      "org.apache.http.conn.routing.HttpRoutePlanner",
      "org.apache.http.conn.ssl.SSLConnectionSocketFactory",
      "org.apache.http.message.LineParser",
      "org.apache.http.impl.cookie.BestMatchSpecFactory",
      "org.apache.http.params.HttpParamsNames",
      "org.apache.http.util.Args",
      "org.apache.http.params.HttpProtocolParams",
      "org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy",
      "org.apache.http.protocol.RequestUserAgent",
      "org.apache.http.conn.socket.LayeredConnectionSocketFactory",
      "org.apache.http.HttpResponseInterceptor",
      "org.apache.http.conn.ssl.BrowserCompatHostnameVerifier",
      "org.apache.http.util.TextUtils",
      "com.ckjava.xutils.http.HttpResult",
      "org.apache.http.impl.EnglishReasonPhraseCatalog",
      "org.apache.http.client.config.RequestConfig$Builder",
      "org.apache.http.auth.AuthenticationException",
      "org.apache.http.auth.AuthState",
      "org.apache.http.client.protocol.RequestAddCookies",
      "org.apache.http.impl.conn.DefaultRoutePlanner",
      "org.apache.http.client.methods.HttpDelete",
      "org.apache.http.impl.conn.CPoolEntry",
      "org.apache.http.client.CookieStore",
      "org.apache.http.impl.auth.BasicSchemeFactory",
      "org.apache.http.conn.socket.PlainConnectionSocketFactory",
      "org.apache.http.client.HttpRequestRetryHandler",
      "org.apache.http.impl.client.CloseableHttpClient",
      "org.apache.http.ParseException",
      "org.apache.http.conn.ssl.TrustStrategy",
      "org.apache.http.client.protocol.ResponseProcessCookies",
      "org.apache.http.message.BasicRequestLine",
      "org.apache.http.client.methods.HttpExecutionAware",
      "org.apache.http.impl.client.InternalHttpClient",
      "org.apache.http.impl.execchain.RetryExec",
      "org.apache.http.conn.routing.RouteInfo",
      "org.apache.http.HttpConnection"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("java.util.function.BiFunction", false, HttpClientUtils_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(HttpClientUtils_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "com.ckjava.xutils.EncodesUtils",
      "com.ckjava.xutils.HttpClientUtils",
      "com.ckjava.xutils.HttpClientUtils$1",
      "org.apache.http.conn.ssl.AbstractVerifier",
      "org.apache.http.conn.ssl.AllowAllHostnameVerifier",
      "org.apache.http.conn.ssl.BrowserCompatHostnameVerifier",
      "org.apache.http.conn.ssl.StrictHostnameVerifier",
      "org.apache.http.conn.ssl.SSLConnectionSocketFactory",
      "org.apache.http.conn.socket.PlainConnectionSocketFactory",
      "org.apache.http.Consts",
      "org.apache.http.util.Args",
      "org.apache.http.util.TextUtils",
      "org.apache.http.entity.ContentType",
      "org.apache.http.protocol.HTTP",
      "org.apache.http.util.VersionInfo",
      "org.apache.http.impl.client.HttpClientBuilder",
      "org.apache.http.conn.ssl.SSLContextBuilder",
      "org.apache.http.conn.ssl.SSLContextBuilder$TrustManagerDelegate",
      "org.apache.http.config.RegistryBuilder",
      "org.apache.http.config.Registry",
      "org.apache.http.impl.conn.PoolingHttpClientConnectionManager",
      "org.apache.commons.logging.impl.Jdk14Logger",
      "org.apache.http.impl.conn.PoolingHttpClientConnectionManager$ConfigData",
      "org.apache.http.pool.AbstractConnPool",
      "org.apache.http.impl.conn.CPool",
      "org.apache.http.impl.conn.PoolingHttpClientConnectionManager$InternalConnectionFactory",
      "org.apache.http.message.BasicLineFormatter",
      "org.apache.http.impl.io.DefaultHttpRequestWriterFactory",
      "org.apache.http.ProtocolVersion",
      "org.apache.http.HttpVersion",
      "org.apache.http.message.BasicLineParser",
      "org.apache.http.impl.EnglishReasonPhraseCatalog",
      "org.apache.http.impl.DefaultHttpResponseFactory",
      "org.apache.http.impl.conn.DefaultHttpResponseParserFactory",
      "org.apache.http.impl.conn.ManagedHttpClientConnectionFactory",
      "org.apache.http.impl.conn.HttpClientConnectionOperator",
      "org.apache.http.impl.conn.DefaultSchemePortResolver",
      "org.apache.http.impl.conn.SystemDefaultDnsResolver",
      "org.apache.http.client.config.RequestConfig$Builder",
      "org.apache.http.client.config.RequestConfig",
      "org.apache.http.protocol.HttpRequestExecutor",
      "org.apache.http.impl.DefaultConnectionReuseStrategy",
      "org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy",
      "org.apache.http.impl.client.AuthenticationStrategyImpl",
      "org.apache.http.impl.client.TargetAuthenticationStrategy",
      "org.apache.http.impl.client.ProxyAuthenticationStrategy",
      "org.apache.http.impl.client.DefaultUserTokenHandler",
      "org.apache.http.impl.execchain.MainClientExec",
      "org.apache.http.impl.auth.HttpAuthenticator",
      "org.apache.http.protocol.ImmutableHttpProcessor",
      "org.apache.http.protocol.RequestTargetHost",
      "org.apache.http.client.protocol.RequestClientConnControl",
      "org.apache.http.conn.routing.BasicRouteDirector",
      "org.apache.http.protocol.HttpProcessorBuilder",
      "org.apache.http.client.protocol.RequestDefaultHeaders",
      "org.apache.http.protocol.RequestContent",
      "org.apache.http.protocol.RequestUserAgent",
      "org.apache.http.client.protocol.RequestExpectContinue",
      "org.apache.http.protocol.ChainBuilder",
      "org.apache.http.client.protocol.RequestAddCookies",
      "org.apache.http.client.protocol.RequestAcceptEncoding",
      "org.apache.http.client.protocol.RequestAuthCache",
      "org.apache.http.client.protocol.ResponseProcessCookies",
      "org.apache.http.client.protocol.ResponseContentEncoding",
      "org.apache.http.impl.execchain.ProtocolExec",
      "org.apache.http.impl.client.DefaultHttpRequestRetryHandler",
      "org.apache.http.impl.execchain.RetryExec",
      "org.apache.http.impl.conn.DefaultRoutePlanner",
      "org.apache.http.impl.client.DefaultRedirectStrategy",
      "org.apache.http.impl.execchain.RedirectExec",
      "org.apache.http.impl.auth.BasicSchemeFactory",
      "org.apache.http.impl.auth.DigestSchemeFactory",
      "org.apache.http.impl.auth.NTLMSchemeFactory",
      "org.apache.http.impl.auth.SPNegoSchemeFactory",
      "org.apache.http.impl.auth.KerberosSchemeFactory",
      "org.apache.http.impl.cookie.BestMatchSpecFactory",
      "org.apache.http.impl.cookie.RFC2965SpecFactory",
      "org.apache.http.impl.cookie.BrowserCompatSpecFactory",
      "org.apache.http.impl.cookie.BrowserCompatSpecFactory$SecurityLevel",
      "org.apache.http.impl.cookie.NetscapeDraftSpecFactory",
      "org.apache.http.impl.cookie.IgnoreSpecFactory",
      "org.apache.http.impl.cookie.RFC2109SpecFactory",
      "org.apache.http.impl.client.BasicCookieStore",
      "org.apache.http.cookie.CookieIdentityComparator",
      "org.apache.http.impl.client.BasicCredentialsProvider",
      "org.apache.http.impl.client.CloseableHttpClient",
      "org.apache.http.impl.client.InternalHttpClient",
      "org.apache.commons.lang3.StringUtils",
      "org.apache.http.message.AbstractHttpMessage",
      "org.apache.http.client.methods.AbstractExecutionAwareRequest",
      "org.apache.http.client.methods.HttpRequestBase",
      "org.apache.http.client.methods.HttpGet",
      "org.apache.http.message.HeaderGroup",
      "org.apache.http.client.methods.HttpEntityEnclosingRequestBase",
      "org.apache.http.client.methods.HttpPost",
      "org.apache.http.client.methods.HttpDelete",
      "com.ckjava.xutils.http.HttpResult",
      "com.ckjava.xutils.ExceptionUtils",
      "org.apache.http.client.methods.HttpPut",
      "org.apache.http.message.BasicHeader",
      "org.apache.http.client.methods.HttpRequestWrapper",
      "org.apache.http.client.methods.HttpRequestWrapper$HttpEntityEnclosingRequestWrapper",
      "org.apache.http.params.AbstractHttpParams",
      "org.apache.http.params.BasicHttpParams",
      "org.apache.http.params.HttpProtocolParams",
      "org.apache.http.message.BasicRequestLine",
      "org.apache.http.protocol.BasicHttpContext",
      "org.apache.http.protocol.HttpCoreContext",
      "org.apache.http.client.protocol.HttpClientContext",
      "org.apache.http.auth.AuthState",
      "org.apache.http.auth.AuthProtocolState",
      "org.apache.http.util.Asserts",
      "org.apache.commons.io.output.StringBuilderWriter",
      "org.apache.commons.io.IOUtils",
      "org.apache.commons.codec.binary.BaseNCodec",
      "org.apache.commons.codec.binary.Base64",
      "org.apache.commons.codec.Charsets",
      "org.apache.commons.codec.binary.Hex",
      "org.apache.commons.codec.DecoderException",
      "org.apache.commons.codec.binary.BaseNCodec$Context",
      "org.apache.http.util.CharArrayBuffer",
      "org.apache.http.message.BufferedHeader",
      "org.apache.http.ParseException",
      "org.apache.http.message.BasicHeaderValueParser",
      "org.apache.http.message.ParserCursor",
      "org.apache.http.message.BasicNameValuePair",
      "org.apache.http.message.BasicHeaderElement",
      "org.apache.http.client.utils.URIUtils",
      "org.apache.http.client.ClientProtocolException"
    );
  }
}
