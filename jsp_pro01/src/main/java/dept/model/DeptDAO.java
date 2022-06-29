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
	
	public DeptDTO searchDeptId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}


	public boolean insertDept(DeptDTO data) {
		int result = session.insert("deptMapper.deptInsert", data);
		
		if(result == 1) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}
	
	
}