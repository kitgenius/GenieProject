package com.genie.dao;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Page<T> {
	/**
	 * 查询所有记录标示
	 */
	public static final int ALL_SIZE = -1;
	/**
	 * 前端展示的页面大小
	 */
	public static final int FRONT_SIZE = 5;
	/**
	 * 第一页
	 */
	public static final int FIRST_PAGE = 1;
	/**
	 * 每页默认显示10条
	 */
	public static final int DEFAULT_SIZE = 10;
	/**
	 * 升序标示
	 */
	public static final String ASC = "asc";
	/**
	 * 降序标示
	 */
	public static final String DESC = "desc";

	/**
	 * 记录总数
	 */
	private int totalCount;
	/**
	 * 每页显示的记录数
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 当前页码
	 */
	private int currentPage;

	/**
	 * 开始记录数
	 */
	private int beginCount;

	private List<T> resultList = Collections.emptyList(); // 基于记录的分页

	/**
	 * 
	 * Description : 重设Page对象
	 * 
	 * @return
	 * 
	 */
	public static Page<String> getBlankPage(int pageSize) {
		Page<String> page = new Page<String>(0, pageSize);
		return page;
	}

	public Page() {

	}

	public Page(List<T> resultList) {
		this.resultList = resultList;
	}

	public Page(int totalCount, int pageSize) {
		this(totalCount, pageSize, 1);
	}

	public Page(int totalCount, int pageSize, int currentPage) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = DEFAULT_SIZE;
		}

		this.totalCount = totalCount;

		if (totalCount <= 0) {
			this.totalPage = 1;
		} else {
			this.totalPage = (totalCount - 1) / pageSize + 1;
		}

		setCurrentPage(currentPage);
	}

	public Page(List<T> resultList, int totalCount, int pageSize, int currentPage) {
		this(totalCount, pageSize, currentPage);
		this.resultList = resultList;
	}

	/**
	 * 
	 * Description : 设置当前是第几页
	 * 
	 * @return
	 * 
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			currentPage = FIRST_PAGE;
		}
		this.currentPage = currentPage;
		this.beginCount = (currentPage - 1) * this.pageSize;
	}

	/**
	 * 
	 * Description : 判断如果没有设置当前页大小，就设置页码大小为默认
	 * 
	 * @return
	 * 
	 */
	@SuppressWarnings("static-access")
	public int getPageSize() {
		if (pageSize <= 0 && pageSize != -1) {
			pageSize = this.DEFAULT_SIZE;
		}
		return pageSize;
	}

	/**
	 * 
	 * Description : 根据pageSize与totalCount计算总页数
	 * 
	 * @return totalPage 总页数
	 * 
	 */
	public int getTotalPage() {
		if (totalCount < 0)
			totalPage = -1;
		if (pageSize == -1) {
			totalPage = 1;
		}
		totalPage = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			totalPage++;
		}

		return totalPage;
	}

	/**
	 * 
	 * Description : 获取当前页，循环后设置成第一页
	 * 
	 * @return currentPage当前页
	 * 
	 */
	@SuppressWarnings("static-access")
	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = this.FIRST_PAGE;
		}
		return currentPage;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setBeginCount(int beginCount) {
		this.beginCount = beginCount;
	}

	public int getBeginCount() {
		return beginCount;
	}
}
