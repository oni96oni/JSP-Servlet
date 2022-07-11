package emps.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.conn.db.DBConn;

public class EmpsDAO {

	private SqlSession session;
	private String mapper = "empsMapper.%s";
	
	
	public EmpsDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<EmpsDTO> searchAll() {
		String mapId = String.format(mapper, "empsSelectAll");
		List<EmpsDTO> datas = session.selectList(mapId);
		return datas;
	}
	
	public List<EmpsDTO> searchPage(int start, int end) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", start);
		page.put("end", end);
		List<EmpsDTO> datas = session.selectList("empsMapper.empsSelectPage", page);
		return datas;
	}
	
	public List<EmpsDTO> searchPage(int start, int end, String sort) {
		Map<String,Integer> page = new HashMap<String,Integer>();
		page.put("start", start);
		page.put("end", end);
		
		switch(sort) {
			case "empId":
				page.put("sort", 1); break;
			case "empName":
				page.put("sort", 2); break;
			case "email":
				page.put("sort", 3); break;
			case "jobName":
				page.put("sort", 4); break;
			case "jobId":
				page.put("sort", 5); break;
			case "deptName":
				page.put("sort", 6); break;
			case "deptId":
				page.put("sort", 7); break;
		}
		
		List<EmpsDTO> datas = session.selectList("empsMapper.empsSelectPage", page);
		return datas;
	}
	
	public int rowCount() {
		int count = session.selectOne("empsMapper.empsRowCount");
		return count;
	}
	
	public EmpsDTO searchEmpsId(int id) {
		EmpsDTO data = session.selectOne("empsMapper.empsSelectId", id);
		return data;
	}

	public boolean insertEmps(EmpsDTO data) {
		int result = session.insert("empsMapper.empsInsert", data);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateEmps(EmpsDTO data) {
		// 업데이트 용 맵퍼와 SQL 구문을 작성하여 이 메서드가 동작하게 한다.
		int result = session.update("empsMapper.empsUpdate", data);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean deleteEmps(int id) {
		int result = session.delete("empsMapper.empsDelete", id);
		
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

	public boolean existDeptId(int deptId) {
		int result = session.selectOne("empsMapper.existDeptId", deptId);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean DeptName(String deptName) {
		int result = session.selectOne("empsMapper.existDeptName", deptName);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existJobId(String jobId) {
		int result = session.selectOne("empsMapper.existJobId", jobId);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existJobName(String jobName) {
		int result = session.selectOne("empsMapper.existJobName", jobName);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existEmail(String email) {
		int result = session.selectOne("empsMapper.existEmail", email);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existEmpName(String empName) {
		int result = session.selectOne("empsMapper.existEmpName", empName);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existEmpId(int empId) {
		int result = session.selectOne("empsMapper.existEmpId", empId);
		if(result == 1) {
			return true;
		}
		return false;
	}

}
