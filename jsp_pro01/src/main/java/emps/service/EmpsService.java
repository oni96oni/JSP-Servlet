package emps.service;

import java.util.ArrayList;
import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;
import dept.service.DEPT_SERVICE_STATUS;
import emps.model.EmpsDAO;
import emps.model.EmpsDTO;

public class EmpsService {
	
	private EmpsDAO dao;
	
	public List<EmpsDTO> getAll() {
		dao = new EmpsDAO();
		List<EmpsDTO> datas = dao.searchAll();
		dao.close();
		return datas;
	}
	
	public List<EmpsDTO> getPage(int pageNumber) {
		int start = (pageNumber - 1) * 10 + 1;
		int end = start + 9;
		
		dao = new EmpsDAO();
		List<EmpsDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<EmpsDTO> getPage(int pageNumber, int count) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		dao = new EmpsDAO();
		List<EmpsDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<EmpsDTO> getPage(int pageNumber, int count, String sort) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		dao = new EmpsDAO();
		List<EmpsDTO> datas = dao.searchPage(start, end, sort);
		dao.close();
		return datas;
	}
	
	public List<Integer> getPageNumberList() {
		dao = new EmpsDAO();
		int rowCount = dao.rowCount();
		dao.close();
		// 여기에 페이지 번호를 가지는 리스트를 만든다.
		List<Integer> pageList = new ArrayList<Integer>();
		int pageNum = (rowCount - 1) / 10;
		for(int n = 0; n <= pageNum; n++) {
			pageList.add(n + 1);
		}
		return pageList;
	}
	
	public List<Integer> getPageNumberList(int count) {
		dao = new EmpsDAO();
		int rowCount = dao.rowCount();
		dao.close();
		// 여기에 페이지 번호를 가지는 리스트를 만든다.
		List<Integer> pageList = new ArrayList<Integer>();
		int pageNum = (rowCount - 1) / count;
		for(int n = 0; n <= pageNum; n++) {
			pageList.add(n + 1);
		}
		return pageList;
	}
	
	public EmpsDTO getEmpsId(String id) {
		boolean isNumber = id.matches("\\d+");
		if(isNumber) {
			int empsId = Integer.parseInt(id);
			dao = new EmpsDAO();
			EmpsDTO data = _getEmpsId(empsId);
			dao.close();
			return data;
		}
		return null;
	}
	
	public EmpsDTO getEmpsId(int id) {
		dao = new EmpsDAO();
		EmpsDTO data = _getEmpsId(id);
		dao.close();
		return data;
	}
	
	private EmpsDTO _getEmpsId(int id) {
		// public 메서드에서 dao 객체를 생성하게 해야함.
		EmpsDTO data = dao.searchEmpsId(id);
		return data;
	}

	public EMPS_SERVICE_STATUS addEmps(EmpsDTO data) {
		dao = new EmpsDAO();
		EMPS_SERVICE_STATUS status = EMPS_SERVICE_STATUS.SUCCESS;
		
		if(!_existEmpId(data.getEmpId())) {
			status = EMPS_SERVICE_STATUS.EMP_ID_DUPLICATED;
		}
		if(!_existEmpName(data.getEmpName())) {
			status = EMPS_SERVICE_STATUS.EMP_NAME_NOT_EXISTS;
		}
		if(!_existEmail(data.getEmail())) {
			status = EMPS_SERVICE_STATUS.EMP_EMAIL_NOT_EXISTS;
		}
		if(!_existJobName(data.getJobName())) {
			status = EMPS_SERVICE_STATUS.JOB_NAME_NOT_EXISTS;
		}
		if(!_existJobId(data.getJobId())) {
			status = EMPS_SERVICE_STATUS.JOB_ID_NOT_EXISTS;
		}
		if(!_existDeptName(data.getDeptName())) {
			status = EMPS_SERVICE_STATUS.DEPT_NAME_NOT_EXISTS;
		}
		if(!_existDeptId(data.getDeptId())) {
			status = EMPS_SERVICE_STATUS.DEPT_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.insertEmps(data)) {
					dao.commit();
				} else {
					status = EMPS_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
			default:
				dao.close();
		}
		return status;
	}
	
	private boolean _existDeptId(int deptId) {
		return dao.existDeptId(deptId);
	}

	private boolean _existDeptName(String deptName) {
		return dao.existDeptName(deptName);
	}

	private boolean _existJobId(String jobId) {
		return dao.existJobId(jobId);
	}

	private boolean _existJobName(String jobName) {
		return dao.existJobName(jobName);
	}

	private boolean _existEmail(String email) {
		return dao.existEmail(email);
	}

	private boolean _existEmpName(String empName) {
		return dao.existEmpName(empName);
	}

	private boolean _existEmpId(int empId) {
		return dao.existEmpId(empId);
	}

	
	public EMPS_SERVICE_STATUS modifyEmps(EmpsDTO data) {
		// addDept 를 구현한 것과 유사하게 수정한 데이터에 대해
		// 문제가 발생한 경우 상세 오류를 구분할 수 있게 만들고
		// Controller 에 반환할 수 있게 한다.
		dao = new EmpsDAO();
		EMPS_SERVICE_STATUS status = EMPS_SERVICE_STATUS.SUCCESS;
		
		if(!_existEmpId(data.getEmpId())) {
			status = EMPS_SERVICE_STATUS.EMP_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.updateEmps(data)) {
					dao.commit();
				} else {
					status = EMPS_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
			default:
				dao.close();
		}
		
		return status;
	}

	public EMPS_SERVICE_STATUS deleteEmps(String id) {
		EMPS_SERVICE_STATUS status = EMPS_SERVICE_STATUS.SUCCESS;
		
		dao = new EmpsDAO();
		
		if(_getEmpsId(Integer.parseInt(id)) == null) {
			status = EMPS_SERVICE_STATUS.EMP_ID_NOT_EXISTS;
		}
		
		boolean result = dao.deleteEmps(Integer.parseInt(id));
		if(result) {
			dao.commit();
		} else {
			status = EMPS_SERVICE_STATUS.FAILED;
			dao.rollback();
		}
		
		dao.close();
		return status;
	}
}
