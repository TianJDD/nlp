package com.ultra.nlp.manage.model;

import java.util.List;
import java.util.Map;

/**
 * 分页模型类
 * 
 * @author guyuefei
 * rowCount 总条数
 */
public class Page {
	private Object obj;//条件bean
	private int pageSize;//分页数
	private int pageNow;//当前页
	private int pageCount;//总页数
	private int rowCount;//总条数
	private int queryStart;//起始页
	private int queryEnd;//结束页
	/** 查询条件 */
	private Object criteriaObj;
	/**  查询条件 map*/
	private Map<String,Object> param;
	/** 结果集 */
	private List<?> resultList;
	/** 结果集合map类型 */
	private Map resultMap;

	public Map getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}

	public Object getCriteriaObj() {
		return criteriaObj;
	}

	public void setCriteriaObj(Object criteriaObj) {
		this.criteriaObj = criteriaObj;
	}

	public Page() {

	}

	public Page(Integer pageNow, Integer pageSize) {
		if(pageNow != null && pageSize != null){
			this.pageSize = pageSize;
			this.pageNow = pageNow;
		}else{
			this.pageSize = 1;
			this.pageNow = 0;
		}
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setQueryStart(int queryStart) {
		this.queryStart = queryStart;
	}

	public void setQueryEnd(int queryEnd) {
		this.queryEnd = queryEnd;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Page(int pageSize, int pageNow, int rowCount) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.rowCount = rowCount;
	}
	public Page(int pageSize, int pageNow, Object obj) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.obj = obj;
	}
	public Page(int pageSize, int pageNow,Map<String,Object> param){
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.param = param;
	}
	public Page(int pageSize, int pageNow, int pageCount, int rowCount) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.pageCount = pageCount;
		this.rowCount = rowCount;
	}
	
	
	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageCount() {
		if (pageSize == 0) {
			return 0;
		}
		if (rowCount % pageSize == 0) {
			pageCount = rowCount / pageSize;
		} else {
			pageCount = (rowCount / pageSize) + 1;
		}
		return pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getQueryStart() {
		if(pageNow!=0&&pageSize!=0)
			queryStart = (pageNow - 1) * pageSize;
		return queryStart;
	}

	public int getQueryEnd() {
		queryEnd = queryStart + pageSize;
		return queryEnd;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

}
