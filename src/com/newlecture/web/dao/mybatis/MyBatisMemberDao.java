package com.newlecture.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.vo.Member;

public class MyBatisMemberDao implements MemberDao {
	
	/*SqlSessionFactory factory = new SqlNewlecSessionFactory().getSqlSessionFactory();*/
	
	@Autowired
	private SqlSession session;
	
	
	/*public void setSession(SqlSession session) {
		this.session = session;
	}*/
	
	@Override
	public Member getMember(String uid) {
		
		/*SqlSession session = factory.openSession();*/
		MemberDao dao = session.getMapper(com.newlecture.web.dao.MemberDao.class);
		
		return dao.getMember(uid);
	}

}
