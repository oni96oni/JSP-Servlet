package dept.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public class DeptDAO {
	
	private SqlSession session;
	
	public DeptDAO() {
		session = DBConn.getSqlSession();
	}
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
}