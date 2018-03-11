package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.ckjava.xutils.ArrayUtils;
import com.ckjava.xutils.db.DbUtils;
import com.ckjava.xutils.db.ObjectListHandler;
import com.ckjava.xutils.db.ObjectListWithColumn;
import com.ckjava.xutils.db.ObjectListWithColumnHandler;

public class TestDbUtils extends DbUtils {

	@Test
	public void testConn() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String path = TestDbUtils.class.getResource("/").getPath();
			String url = "jdbc:sqlite:".concat(path).concat("test.db");
			conn = DriverManager.getConnection(url);
			System.out.println("Opened database successfully");
			assertTrue(conn != null);
			
			StringBuilder sqls = new StringBuilder();
			sqls.append(" drop table user; ");
			sqls.append(" create TABLE user(id integer PRIMARY KEY AUTOINCREMENT,name varchar(255),age int,email varchar(255)); ");
			sqls.append(" insert into user(name,age,email) values('张三',25,'zhangsan@gmail.com'); ");
			sqls.append(" insert into user(name,age,email) values('李四',26,'lisi@gmail.com'); ");
			sqls.append(" insert into user(name,age,email) values('ammy',27,'ammy@163.com'); ");
			executeMutiSql(conn, sqls.toString(), Connection.TRANSACTION_SERIALIZABLE);
			
			closeResource(conn, null, null);
			assertTrue(conn.isClosed());
			
			// 测试 ObjectListWithColumnHandler
			DataSource ds = createDataSource(url, null, null);
			QueryRunner sqlRunner = new QueryRunner(ds);
			// 执行查询任务
			String querySql = "select id, name, age as 年龄, email as 电子邮件 from user";
			ObjectListWithColumn result = sqlRunner.query(querySql, new ObjectListWithColumnHandler());
			result.setSql(querySql);
			result.setDesc("查询成功,数量="+result.getDataList().size());
			Object[] column = result.getColumn();
			assertTrue(ArrayUtils.getValue(column, 0).equals("id"));
			assertTrue(ArrayUtils.getValue(column, 1).equals("name"));
			assertTrue(ArrayUtils.getValue(column, 2).equals("年龄"));
			assertTrue(ArrayUtils.getValue(column, 3).equals("电子邮件"));
			
			List<Object[]> dataList = result.getDataList();
			assertTrue(dataList.size() == 3);
			
			// 测试 ObjectListHandler
			dataList = sqlRunner.query(querySql, new ObjectListHandler());
			assertTrue(dataList.size() == 3);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}

}
