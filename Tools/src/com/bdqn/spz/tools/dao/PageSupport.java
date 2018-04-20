package com.bdqn.spz.tools.dao;

import java.util.List;
/**
 * 分页工具类  使用范型集合
 * @author 包小栋
 *
 */
public class PageSupport<T> {
	private Integer pageNo;  //当前页
	private Integer pageSize; //页大小
	private Integer maxPage;  //最大页
	private Integer countToal;//总数量
	private List<T>list;  	//展示集合
	//无参构造
	public PageSupport() {
		super();
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}
	public Integer getCountToal() {
		return countToal;
	}
	public void setCountToal(Integer countToal) {
		this.countToal = countToal;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageSupport(Integer pageNo, Integer pageSize, Integer maxPage,
			Integer countToal, List<T> list) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.maxPage = maxPage;
		this.countToal = countToal;
		this.list = list;
	}
}
