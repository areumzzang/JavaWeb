package com.newlecture.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.vo.Notice;

public class NoticeConsole { 
	
		 NoticeDao noticeDao;
	
	@Autowired
	@Resource(name="noticeDao")
	public void setNoticeDao(NoticeDao noticeDao) {
			this.noticeDao = noticeDao;
		}
	
	
	public void print() { //메인함수 추가한 Program클래스를 만들어라.
		List<Notice> list = noticeDao.getNotices();  //config에 mybatis매퍼를 갖고있지않아서
		//MyBatisNoticeDao() 강한결합을 갖고 있는 상태에서는 스프링이 의미가 없다.
		
		for(Notice n : list)
			System.out.printf("title : %s\n", n.getTitle());
		
	}

}
