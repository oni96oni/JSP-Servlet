package locs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public class LocsDAO {
	
	private SqlSession session;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}
	
	
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	
	public LocsDTO searchLocsId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}
	
	
}