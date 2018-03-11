package com.ckjava.xutils.http;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页实体")
public class Page<T> {

	@ApiModelProperty("起始行数")
	private Integer start;
	@ApiModelProperty("每页记录数, 默认30")
	private Integer pageSize = 30;
	@ApiModelProperty("总记录数")
	private Integer totalCount;
	@ApiModelProperty("总页数")
	private Integer pages;
	@ApiModelProperty("当前页记录")
	private List<T> dataList;
	
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public Page(Integer start, Integer pageSize, Integer totalCount, List<T> dataList) {
		super();
		this.start = start;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.dataList = dataList;
		
		if (totalCount == 0) {
			this.pages = 1;
		} else if (totalCount % pageSize == 0) {
			this.pages = totalCount / pageSize;
		} else {
			this.pages = totalCount / pageSize + 1;
		}
	}

	/**
	 * 获取分页对象
	 * 
	 * @param <T> 任意对象
	 * @param start 起始行数
	 * @param pageSize 每页记录数, 默认30
	 * @param totalCount 总记录数
	 * @param dataList 当前页记录
	 * @return {@code Page<T> } 分页对象 
	 */
	public static <T> Page<T> getPage(Integer start, Integer pageSize, Integer totalCount, List<T> dataList) {
		return new Page<T>(start, pageSize, totalCount, dataList);
	}
}
