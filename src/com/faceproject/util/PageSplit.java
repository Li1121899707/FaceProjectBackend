package com.faceproject.util;

public class PageSplit {
	public PageUtil getPageIndex(int currIndex, int currNumber) {
		int pageSize = 8;

		PageUtil page = new PageUtil();
		page.setPageIndex(currIndex);
		page.setPageNumber(currNumber);
		page.setPageSize(pageSize);

		if ((int) page.getPageNumber() % (int) page.getPageSize() == 0) {
			page.setPageCount((int) Math.ceil((double) (page.getPageNumber() / page.getPageSize())));
		} else {
			page.setPageCount((int) Math.ceil((double) (page.getPageNumber() / page.getPageSize())) + 1);
		}

		int daoIndex = (currIndex - 1) * pageSize;
		page.setDaoIndex(daoIndex);
		return page; // 数据库所在位置
	}
}
