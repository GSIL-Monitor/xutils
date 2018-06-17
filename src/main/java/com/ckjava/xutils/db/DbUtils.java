package com.ckjava.xutils.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ckjava.xutils.StringUtils;

/**
 * 数据库相关的帮助类
 * 
 * <ul>
 * <li>根据 dburl,username,password 获取数据源 {@code DataSource } </li>
 * <li>根据 数据源 可以获取到数据库的 所有表 的列表</li>
 * <li>根据 数据源 和 表名 获取到表的详细信息</li>
 * <li>根据 数据源 和 sql 获取到查询结果</li>
 * <li>根据 数据源 和 sql 获取到查询结果,并且带有列名</li>
 * <li></li>
 * <li></li>
 * </ul>
 * 
 * @author chen_k
 *
 * 2018年1月31日-下午7:22:54
 */
public class DbUtils extends StringUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DbUtils.class);
	
	private static Map<String, DataSource> dataSourceMap = Collections.synchronizedMap(new HashMap<String, DataSource>());
	
	/**
	 * 根据 db url, 用户名, 密码创建数据源, 并将其存入到类缓存中, 自动根据 url 中的定义加载驱动类
	 * 
	 * @param url 数据库连接 url, 包含数据库主机名,端口号,数据库
	 * @param username 数据库用户名
	 * @param password 数据库密码
	 * @return {@code DataSource}
	 * @throws Exception 异常对象
	 */
	public static DataSource createDataSource(String url, String username, String password) throws Exception {
		String dbKey = getDbKey(url, username, password);
		DataSource ds = dataSourceMap.get(dbKey);
		if (ds == null) {
			DatabasePoolConfig poolConfig = new DatabasePoolConfig();
			
			PoolProperties p = poolConfig.getPoolProperties();
			p.setUrl(url);
	        p.setUsername(username);
	        p.setPassword(password);
	        
	        if (url.contains("jdbc:mysql")) {
	        	p.setDriverClassName("com.mysql.jdbc.Driver");
	        } else if (url.contains("jdbc:sqlserver")) {
	        	p.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        } else if (url.contains("jdbc:sqlite")) {
	        	p.setDriverClassName("org.sqlite.JDBC");
	        } else {
	        	throw new RuntimeException(MessageFormat.format("can't load DriverClass by url:{0}", url));
	        }
			
	        org.apache.tomcat.jdbc.pool.DataSource newDS = new org.apache.tomcat.jdbc.pool.DataSource(p);
	        
	        dataSourceMap.put(dbKey, newDS);
	        
			return newDS;	
		} else {
			return ds;
		}
	}
	
	private static String getDbKey(String url, String username, String password) {
		StringBuilder str = new StringBuilder();
		str.append(url).append(CONCAT.AT).append(username).append(CONCAT.AT).append(password);
		return str.toString();
	}
	
	/**
	 * 执行多条sql
	 * 
	 * TransactionIsolation 为 TRANSACTION_READ_COMMITTED
	 * 
	 * @param conn {@code Connection} 对象
	 * @param sql 以 ; 分隔的 sql
	 */
	public static void executeMutiSql(Connection conn, String sql) {
		int rowcount = 0;
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			String tempSql = sql.replaceAll(";(\\s*|\t|\r|\n)(?i)insert", "';'insert")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)update", "';'update")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)delete", "';'delete")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)drop", "';'drop")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)create", "';'create");
			String[] sqls = tempSql.split("\';\'");
			for (String sqlstr : sqls) {
				ps = conn.prepareStatement(sqlstr);
				rowcount = ps.executeUpdate();
				logger.info("执行sql：{},受影响行数：{}", new Object[]{sqlstr, rowcount});
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			logger.error("执行sql出现异常", e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚出现异常", e1);
			}
			rowcount = 0;
		} finally {
			closeResource(conn, ps, null);
		}
	}
	
	public static int executeMutiSql(Connection conn, String sql, int transactionLevel) {
		int sum = 0;
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(transactionLevel);
			String tempSql = sql.replaceAll(";(\\s*|\t|\r|\n)(?i)insert", "';'insert")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)update", "';'update")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)delete", "';'delete")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)drop", "';'drop")
					.replaceAll(";(\\s*|\t|\r|\n)(?i)create", "';'create");
			String[] sqls = tempSql.split("\';\'");
			for (String sqlstr : sqls) {
				PreparedStatement ps = conn.prepareStatement(sqlstr);
				int rowcount = ps.executeUpdate();
				sum += rowcount;
				logger.info("执行sql：{},受影响行数：{}", new Object[]{sqlstr, rowcount});
			}
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException e) {
			logger.error("执行sql出现异常", e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚出现异常", e1);
			}
			sum = 0;
		} finally {
			closeResource(conn, null, null);
			return sum;
		}
	}
	
	/**
	 * 关闭数据库相关资源
	 * 
	 * @param conn {@code Connection} 对象
	 * @param ps {@code PreparedStatement} 对象
	 * @param rs {@code ResultSet} 对象
	 */
	public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("关闭数据库资源 Connection 出现异常", e);
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error("关闭数据库资源 PreparedStatement 出现异常", e);
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("关闭数据库资源 ResultSet 出现异常", e);
			}
		}
	}

}
