package com.lulu.util;

public class Page {
	private int pageSize=5; //  ① 每页显示多少条数据   固定的
	private int totalPage=0;//  ② 总的页数   需要计算的   总的页数=总的数据行数/每页的行数
	private int totalCount=0;// ③ 总的数据行数   select语句查询而来
	private int pageNumber=1;// ④ 当前页的页码  从界面传过来
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() { // EL表达式中 自动调用get方法
		// 计算总的页数
		if(totalCount % pageSize == 0){
			return totalCount / pageSize;
		}else{
			return totalCount / pageSize + 1;
		}
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

}

