package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.web.dao.NoticeFileDao;
import com.newlecture.web.vo.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {
	
	@Autowired
	private SqlSession session;
	
	/*@Autowired
	public void setSession(SqlSession session) {
		this.session = session;
	}*/
	
	/*SqlSessionFactory factory = new SqlNewlecSessionFactory().getSqlSessionFactory();*/

	
	@Override
	public int addNoticeFile(NoticeFile noticeFile) {
		int result = 0;
		//SqlSession session = factory.openSession();//false�� �⺻�� true�� �ְԵǸ� auto commit; true�� �ȳ����� commit;�� ������� �Ѵ�.
		
		try{
		NoticeFileDao dao = session.getMapper(com.newlecture.web.dao.NoticeFileDao.class);
		result = dao.addNoticeFile(noticeFile);
		//session.commit();
		}
		finally {
			//session.rollback();
			//session.close();
		}
		
		return result;
	}
	
	public List<NoticeFile> getNoticeFilesOfNotice(String code){
		
		/*List<NoticeFile> list = new ArrayList<NoticeFile>();
		NoticeFile file = new NoticeFile();
		file.setName(code+"�� ����1");
		list.add(file);
		
		file = new NoticeFile();
		file.setName(code+"�� ����2");
		list.add(file);
		return list;*/
		
		//SqlSession session = factory.openSession();
		NoticeFileDao dao = (NoticeFileDao)session.getMapper(com.newlecture.web.dao.NoticeFileDao.class);
		List<NoticeFile> list = dao.getNoticeFilesOfNotice(code);
		//session.close();
		
		return list;
	}	

}
