package board.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public class EmpBoardDAO {
	private SqlSession session = null;
	private String mapper = "empBoardMapper.%s";
	
	public EmpBoardDAO() {
		this.session = DBConn.getSqlSession();
	}
	
	public int getNextSeq() {
		String mapperId = String.format(mapper, "getNextSeq");
		int seq = session.selectOne(mapperId);
		return seq;
	}
	
	public boolean insertData(EmpBoardDTO data) {
		String mapperId = String.format(mapper, "insertData");
		int res = session.insert(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public EmpBoardDTO selectData(int id) {
		String mapperId = String.format(mapper, "selectData");
		EmpBoardDTO res = session.selectOne(mapperId, id);
		return res;
	}
	
	public boolean updateViewCnt(EmpBoardDTO data) {
		String mapperId = String.format(mapper, "updateViewCnt");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateLike(EmpBoardDTO data) {
		String mapperId = String.format(mapper, "updateLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public EmpBoardStaticsDTO selectStatics(EmpBoardStaticsDTO staticsData) {
		String mapperId = String.format(mapper, "selectStatics");
		EmpBoardStaticsDTO data = session.selectOne(mapperId, staticsData);
		return data;
	}
	
	public boolean insertStatics(EmpBoardStaticsDTO staticsData) {
		String mapperId = String.format(mapper, "insertStatics");
		int res = session.insert(mapperId, staticsData);
		return res == 1 ? true : false;
	}
	
	public boolean updateStatics(EmpBoardStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStatics");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean updateStaticsLike(EmpBoardStaticsDTO data) {
		String mapperId = String.format(mapper, "updateStaticsLike");
		int res = session.update(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public List<EmpBoardDTO> selectAll() {
		String mapperId = String.format(mapper, "selectAll");
		List<EmpBoardDTO> res = session.selectList(mapperId);
		return res;
	}
	
	public boolean deleteStaticsData(EmpBoardStaticsDTO data) {
		String mapperId = String.format(mapper, "deleteStaticsData");
		int res = session.delete(mapperId, data);
		return res >= 0 ? true : false;
	}
	
	public boolean deleteData(EmpBoardDTO data) {
		String mapperId = String.format(mapper, "deleteData");
		int res = session.delete(mapperId, data);
		return res == 1 ? true : false;
	}
	
	public boolean update(EmpBoardDTO data) {
		String mapperId = String.format(mapper, "updateData");
		int res = session.update(mapperId, data);
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
