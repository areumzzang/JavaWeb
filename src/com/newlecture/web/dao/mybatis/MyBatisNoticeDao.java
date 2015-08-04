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
	
	/*�ι�° ���
	 * @Autowired
	 * private SqlSession session;
	 */
	
	/*ù��° ���
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
	public Notice getNotice(String code) { //�ڼ��� ������
		
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
		/*SqlSession session = factory.openSession();*///false�� �⺻�� true�� �ְԵǸ� auto commit; true�� �ȳ����� commit;�� ������� �Ѵ�.
		
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
