package com.spz.tools.tool.dao;

import java.util.List;
/**
 * ��ҳ������  ʹ�÷��ͼ���
 * @author ��С��
 *
 */
public class PageSupport<T> {
	private Integer pageNo;  //��ǰҳ
	private Integer pageSize; //ҳ��С
	private Integer maxPage;  //���ҳ
	private Integer countToal;//������
	private List<T>list;  	//չʾ����
	//�޲ι���
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
