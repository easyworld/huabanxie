package com.dhu.huabanxie.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Page implements Serializable {

	private static final long serialVersionUID = -2975057936500090018L;
	private static int DEFAULT_PAGE_SIZE = 20;
	private int pageSize = DEFAULT_PAGE_SIZE;// 每页记录数
	private long start;// 当前页第一条数据在List中的位置,从0开始
	private List<Map<String, Object>> data;// 分页的记录
	private long totalSize;// 总记录数

	public long getTotalSize() {
		return totalSize;
	}

	/**
	 * 构造空页
	 */
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<Map<String, Object>>());
	}

	public Page(long start, long totalSize, int pageSize,
			List<Map<String, Object>> data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalSize = totalSize;
		this.data = data;
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public long getTotalPageCount() {
		if (totalSize % pageSize == 0)
			return totalSize / pageSize;
		else
			return totalSize / pageSize + 1;
	}

	/**
	 * 获取当前页码
	 * 
	 * @return
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}
	/**
	 * 返回Page对象里面的list
	 * @return
	 */
	public List<Map<String, Object>> getList() {
		return this.data;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值
	 * 
	 * @param pageNo
	 * @return
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
