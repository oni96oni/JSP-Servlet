package common.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public abstract class AbstractDAO<C, E> {

	protected SqlSession session = DBConn.getSqlSession();
	
	public abstract C selectAll();
	public abstract E selectId(E e);
	public abstract E selectId(int id);
	public abstract int rowCount();
	public abstract boolean insertData(E e);
	public abstract boolean updateData(E e);
	public abstract boolean deleteData(int id);
	public abstract boolean deleteData(E e);
	
	public void commit() {
		session.commit();
	}
	
	public void rollback() {
		session.rollback();
	}
	
	public void close( ) {
		session.close();
	}
}
