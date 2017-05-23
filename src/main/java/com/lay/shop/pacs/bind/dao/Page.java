package com.lay.shop.pacs.bind.dao;

import java.io.Serializable;

public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6900835810451940829L;
	
//	private int start;
	private int size = 20;
	private int startPage=1;
	

	public Page(){}
	
	public Page(int startPage, int size){
//		this.start = (startPage - 1)*size;
		this.startPage=startPage;
		this.size = size;
	}
	

	public int getStart() {
		return (startPage - 1)*size;
	}
	public void setStart(int start) {
		this.startPage=(start+size-1)/size;
	}
	public int getPage() {
		return startPage;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public void setPage(int startPage) {
		this.startPage=startPage;
		
	}

	/**
	 * startPage的获取.
	 * @return int
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * 设定startPage的值.
	 * @param int
	 */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
}
