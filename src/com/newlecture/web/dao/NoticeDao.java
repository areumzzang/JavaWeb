package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.vo.Notice;

public interface NoticeDao {
	public List<Notice> getNotices(int page, String field, String query);
	public List<Notice> getNotices(int page);
	public List<Notice> getNotices();
	public Notice getNotice(String code);
	public int addNotice(Notice notice); //insert, delete, update는 여러개를 수행할 수 있으므로 보통 int값으로 반환값을 준다.
	public String getLastCode();
	
	
}
