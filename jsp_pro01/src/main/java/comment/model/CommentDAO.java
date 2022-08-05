package comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public class CommentDAO {
	private SqlSession session = DBConn.getSqlSession();
	private String mapper = "commentMapper.%s";
	
	public boolean insertData(CommentDTO data) {
		String mapId = String.format(mapper, "insertData");
		int res = session.insert(mapId, data);
		return res == 1 ? true : false;
	}
	
	public CommentDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		CommentDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public List<CommentDTO> selectDatas(int id) {
		String mapperId = String.format(mapper, "selectDatas");
		List<CommentDTO> res = session.selectList(mapperId, id);
		return res;
	}
	
	public boolean deleteId(int id) {
		String mapId = String.format(mapper, "deleteId");
		int res = session.delete(mapId, id);
		return res == 1 ? true : false;
	}
	
	public boolean deleteData(CommentDTO data) {
		String mapId = String.format(mapper, "deleteData");
		int res = session.update(mapId, data);
		return res == 1 ? true : false;
	}
	
	public void commit() {
		this.session.commit();
	}
	
	public void rollback() {
		this.session.rollback();
	}
	
	public void close() {
		this.session.close();
	}



}
