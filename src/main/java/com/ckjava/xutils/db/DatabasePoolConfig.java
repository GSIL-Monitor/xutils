package com.ckjava.xutils.db;

import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DatabasePoolConfig {
	
	private String name = null;
	private PoolProperties poolProperties = null;
	private String option = null;
	
	public static final boolean DEFAULT_TESTWHILEIDLE = true;
	public static final boolean DEFAULT_TESTONBORROW = false;
	public static final boolean DEFAULT_TESTONRETURN = false;
	public static final String DEFAULT_VALIDATIONQUERY = "SELECT 1";
	public static final long DEFAULT_VALIDATIONINTERVAL = 30000L;
	public static final int DEFAULT_TIMEBETWEENEVICTIONRUNSMILLIS = 30000;
	public static final int DEFAULT_MAXACTIVE = 100;
	public static final int DEFAULT_MINIDLE = 10;
	public static final int DEFAULT_MAXWAIT = 10000;
	public static final int DEFAULT_INITIALSIZE = 10;
	public static final int DEFAULT_REMOVEABANDONEDTIMEOUT = 60;
	public static final boolean DEFAULT_REMOVEABANDONED = true;
	public static final boolean DEFAULT_LOGABANDONED = true;
	public static final int DEFAULT_MINEVICTABLEIDLETIMEMILLIS = 30000;
	public static final String DEFAULT_CONNECTIONPROPERTIES = null;
	public static final boolean DEFAULT_JMXENABLED = true;
	public static final String DEFAULT_JDBCINTERCEPTORS = "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
	          "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer";
	
	public DatabasePoolConfig() {
		poolProperties = new PoolProperties();
		poolProperties.setTestWhileIdle(DEFAULT_TESTWHILEIDLE);
		poolProperties.setTestOnBorrow(DEFAULT_TESTONBORROW);
		poolProperties.setTestOnReturn(DEFAULT_TESTONRETURN);
		poolProperties.setValidationQuery(DEFAULT_VALIDATIONQUERY);
		poolProperties.setValidationInterval(DEFAULT_VALIDATIONINTERVAL);
		poolProperties.setTimeBetweenEvictionRunsMillis(DEFAULT_TIMEBETWEENEVICTIONRUNSMILLIS);
		poolProperties.setMaxActive(DEFAULT_MAXACTIVE);
		poolProperties.setMinIdle(DEFAULT_MINIDLE);
		poolProperties.setMaxWait(DEFAULT_MAXWAIT);
		poolProperties.setInitialSize(DEFAULT_INITIALSIZE);
		poolProperties.setRemoveAbandonedTimeout(DEFAULT_REMOVEABANDONEDTIMEOUT);
		poolProperties.setRemoveAbandoned(DEFAULT_REMOVEABANDONED);
		poolProperties.setLogAbandoned(DEFAULT_LOGABANDONED);
		poolProperties.setMinEvictableIdleTimeMillis(DEFAULT_MINEVICTABLEIDLETIMEMILLIS);
		poolProperties.setConnectionProperties(DEFAULT_CONNECTIONPROPERTIES);
		poolProperties.setJmxEnabled(DEFAULT_JMXENABLED);
		poolProperties.setJdbcInterceptors(DEFAULT_JDBCINTERCEPTORS);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PoolProperties getPoolProperties() {
		return poolProperties;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
}