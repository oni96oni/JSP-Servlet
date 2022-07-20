package locs.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

import dept.model.DeptDTO;

public class LocsDAO {
	
	private SqlSession session;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}
	
	
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	
	public List<LocsDTO> searchPage(int start, int end) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", start);
		page.put("end", end);
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectPage", page);
		return datas;
	}
	
	public List<LocsDTO> searchPage(int start, int end, String sort) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", start);
		page.put("end", end);
		
		switch(sort) {
			case "locsId":
				page.put("sort", 1); break;
			case "strAdd":
				page.put("sort", 2); break;
			case "posCode":
				page.put("sort", 3); break;
			case "city":
				page.put("sort", 4); break;
			case "staPro":
				page.put("sort", 5); break;
			case "conId":
				page.put("sort", 6); break;
		}
			List<LocsDTO> datas = session.selectList("locsMapper.locsSelectPage", page);
			return datas;
	}
	
	
	public int rowCount() {
		int count = session.selectOne("locsMapper.locsRowCount");
		return count;
	}
	
	public LocsDTO searchLocsId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}


	public boolean insertLocs(LocsDTO data) {
		if(session.insert("locsMapper.locsInsert", data)==1) {
			return true;
		}
		return false;
	}
	
	public void commit() {
		session.commit();
	}
	
	public void rollback() {
		session.rollback();
	}
	
	public void close() {
		session.close();
	}


	public boolean existConId(String id) {
		int result = session.selectOne("locsMapper.existConId", id);
		if(result == 1) {
			System.out.println("데이터접근성공");
			return true;
		}
		return false;
	}
	
	public boolean updateLocs(LocsDTO data) {
		// 업데이트 용 맵퍼와 SQL 구문을 작성하여 이 메서드가 동작하게 한다.
		int result = session.update("locsMapper.locsUpdate", data);
		if(result == 1) {
			System.out.println("업데이트접근성공");
			return true;
		}
		return false;
	}


	public boolean deleteLocs(int id) {
		int result = session.delete("locsMapper.locsDelete", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
}