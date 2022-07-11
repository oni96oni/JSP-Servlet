package dept.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
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
	
	public List<DeptDTO> searchPage(int start, int end) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", start);
		page.put("end", end);
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	public List<DeptDTO> sortPage(int start, int end, String sort) {
		Map page = new HashMap();
		page.put("start", start);
		page.put("end", end);
		page.put("sort", sort);
		System.out.println("session.selectList실행전" + sort);
		System.out.println(start+"값과 end값은"+end);
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPageOrder", page);
		System.out.println("session.selectList(deptMapper.deptSelectPageOrder, page) 실행" + datas.toString());
		return datas;
	}
	
	public int rowCount() {
		int count = session.selectOne("deptMapper.deptRowCount");
		return count;
	}
	
	public DeptDTO searchDeptId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	public boolean insertDept(DeptDTO data) {
		int result = session.insert("deptMapper.deptInsert", data);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateDept(DeptDTO data) {
		// 업데이트 용 맵퍼와 SQL 구문을 작성하여 이 메서드가 동작하게 한다.
		int result = session.update("deptMapper.deptUpdate", data);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean existManager(int id) {
		int result = session.selectOne("deptMapper.existManager", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean existLocation(int id) {
		int result = session.selectOne("deptMapper.existLocation", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteDept(int id) {
		int result = session.delete("deptMapper.deptDelete", id);
		
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
