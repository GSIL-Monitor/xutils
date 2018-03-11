package com.ckjava.xutils.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 将 ResultSet 对象转换成 {@code ObjectListWithColumn}, 其中含有查询返回的 列名和数据集
 * 
 * @author chen_k
 *
 *         2018年1月3日-下午7:05:55
 */
public class ObjectListWithColumnHandler implements ResultSetHandler<ObjectListWithColumn> {

	public ObjectListWithColumn handle(ResultSet rs) throws SQLException {
		ObjectListWithColumn objWithColumn = new ObjectListWithColumn();
		List<Object[]> dataList = new ArrayList<Object[]>();
		objWithColumn.setDataList(dataList);
		// 获取列名
		ResultSetMetaData meta = rs.getMetaData();
		int cols = meta.getColumnCount();
		Object[] columnName = new Object[cols];
		for (int i = 0; i < cols; i++) {
			columnName[i] = meta.getColumnLabel(i + 1);
		}
		objWithColumn.setColumn(columnName);
		// 获取查询结果
		while (rs.next()) {
			Object[] rowData = new Object[cols];
			for (int i = 0; i < cols; i++) {
				rowData[i] = rs.getObject(i + 1);
			}
			dataList.add(rowData);
		}

		return objWithColumn;
	}
}
