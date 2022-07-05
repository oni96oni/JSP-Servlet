package board.model;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public class BoardDAO {
	private SqlSession session;
		
	public BoardDAO() {
		session = DBConn.getSqlSession();
	}
	
	public boolean insertBoard(Board data) {
		int result = session.insert("boardMapper.boardInsert", data);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateBoard(Board data) {
		int result = session.update("boardMapper.boardUpdate", data);
		
		if(result == 1) {
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
}
