package com.ckjava.xutils.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 将  ResultSet 对象转换成  {@code List<Object[]>, Object[] } 表示表中的每一行记录
 * 
 * @author chen_k
 *
 * 2018年1月3日-下午7:05:55
 */
public class ObjectListHandler implements ResultSetHandler<List<Object[]>> {

	public List<Object[]> handle(ResultSet rs) throws SQLException {
		List<Object[]> resultList = new ArrayList<Object[]>();
		
		while (rs.next()) {
			ResultSetMetaData meta = rs.getMetaData();
	        int cols = meta.getColumnCount();
	        Object[] result = new Object[cols];
	        for (int i = 0; i < cols; i++) {
	            result[i] = rs.getObject(i + 1);
	        }
	        
	        resultList.add(result);
		}

		return resultList;
	}

}
