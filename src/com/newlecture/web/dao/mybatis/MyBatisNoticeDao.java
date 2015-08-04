package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.Notice;



public class MyBatisNoticeDao implements NoticeDao{
	
	@Autowired
	private SqlSession session;
	
	/*@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}*/

	/*SqlSessionFactory factory = new SqlNewlecSessionFactory().getSqlSessionFactory();*/
	
	/*두번째 경우
	 * @Autowired
	 * private SqlSession session;
	 */
	
	/*첫번째 경우
	 * private SqlSession session;
	
	@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}*/

	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		
		/*SqlSession session = factory.openSession();*/
		NoticeDao dao = session.getMapper(com.newlecture.web.dao.NoticeDao.class);
		NoticeFileDao fileDao = session.getMapper(com.newlecture.web.dao.NoticeFileDao.class);
		
		List<Notice> list = dao.getNotices(page, field, query);
		
		for(Notice n : list){
				n.setFiles(fileDao.getNoticeFilesOfNotice(n.getCode()));
		}

		return list;
	}

	@Override
	public Notice getNotice(String code) { //자세한 페이지
		
		/*SqlSession session = factory.openSession();*/
		NoticeDao dao = session.getMapper(com.newlecture.web.dao.NoticeDao.class);
	
		return dao.getNotice(code);
	}

	@Override
	public List<Notice> getNotices(int page) {
		/*SqlSession session = factory.openSession();*/
		NoticeDao dao = session.getMapper(com.newlecture.web.dao.NoticeDao.class);
		
		return dao.getNotices(page, "TITLE", "");
	}

	@Override
	public List<Notice> getNotices() {
		/*SqlSession session = factory.openSession();*/
		NoticeDao dao = session.getMapper(com.newlecture.web.dao.NoticeDao.class);
		
		return dao.getNotices(1, "TITLE", "");
	}

	@Override
	public int addNotice(Notice notice) {
		
		int result = 0;
		/*SqlSession session = factory.openSession();*///false가 기본값 true를 넣게되면 auto commit; true를 안넣으면 commit;을 시켜줘야 한다.
		
		try{
		NoticeDao dao = session.getMapper(com.newlecture.web.dao.NoticeDao.class);
		result = dao.addNotice(notice);
		//session.commit();
		}
		finally {
			//session.rollback();
			//session.close();
		}
		
		return result;
	}

	@Override
	public String getLastCode() {
		/*SqlSession session = factory.openSession();*/
		NoticeDao dao = session.getMapper(com.newlecture.web.dao.NoticeDao.class);
	
		return dao.getLastCode();
	}




	
}
