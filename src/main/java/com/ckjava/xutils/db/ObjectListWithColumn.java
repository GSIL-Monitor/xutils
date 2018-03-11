package com.ckjava.xutils.db;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * column 表示列名
 * dataList 表示表中的数据集
 * 
 * @author chen_k
 *
 * 2018年1月3日-下午7:07:43
 */
@ApiModel("带有列名的数据库查询结果")
public class ObjectListWithColumn {

	@ApiModelProperty("列名 Object[]")
	private Object[] column;
	@ApiModelProperty("数据集合 List<Object[]> ")
	private List<Object[]> dataList;
	@ApiModelProperty("数据库连接信息")
	private String dbUrl;
	@ApiModelProperty("查询语句")
	private String sql;
	@ApiModelProperty("查询描述")
	private String desc;

	public Object[] getColumn() {
		return column;
	}
	public void setColumn(Object[] column) {
		this.column = column;
	}
	public List<Object[]> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object[]> dataList) {
		this.dataList = dataList;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
