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

	private String mapper = "empsMapper.%s";
	private SqlSession session;
	
	
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
	
	public EmpsDetailDTO selectEmpDetail(int empId) {
		String mapId = String.format(mapper, "selectEmpDetail");
		EmpsDetailDTO data = session.selectOne(mapId, empId);
		System.out.println("data.getEmpId = " + data.getEmpId());
		return data;
	}
	
	public EmpsDTO searchEmpsId(int id) {
		EmpsDTO data = session.selectOne("empsMapper.empsSelectId", id);
		return data;
	}
	
	public boolean insertEmp(EmpsDTO empsData) {
		//이름을 firstName, lastName 으로 구분해주어야 한다. 이미 객체로 넣는데 DTO를 수정해야하는걸까?
		//이메일도 @이후의 주소는 없애주어야 한다.
		//hireDate도 넣어주어야 한다 지금시점기준으로 넣어주기
		
		//필수 입력사항 - EMPLOYEE_ID, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID, 나머지는 NULL허용
		String mapId = String.format(mapper, "insertEmp");
		int result = session.update(mapId, empsData);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean insertEmps(EmpsDTO data) {
		//이름을 firstName, lastName 으로 구분해주어야 한다. 이미 객체로 넣는데 DTO를 수정해야하는걸까?
		//이메일도 @이후의 주소는 없애주어야 한다.
		//hireDate도 넣어주어야 한다 지금시점기준으로 넣어주기
		
		//필수 입력사항 - EMPLOYEE_ID, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID, 나머지는 NULL허용
		
		int result = session.insert("empsMapper.empsInsert", data);
		
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateEmp(EmpsDTO empsData) {
		String mapId = String.format(mapper, "updateEmp");
		int result = session.update(mapId, empsData);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateEmpDetail(EmpsDetailDTO empsDetailData) {
		String mapId = String.format(mapper, "updateEmpDetail");
		int result = session.update(mapId, empsDetailData);
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

	public boolean existDeptName(String deptName) {
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
