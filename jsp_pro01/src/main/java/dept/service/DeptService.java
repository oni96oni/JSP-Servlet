package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	
	private DeptDAO dao;
	
	public List<DeptDTO> getAll() {
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchAll();
		dao.close();
		return datas;
	}
	
	public DeptDTO getDeptId(String id) {
		boolean isNumber = id.matches("\\d+");
		if(isNumber) {
			int deptId = Integer.parseInt(id);
			dao = new DeptDAO();
			DeptDTO data = _getDeptId(deptId);
			dao.close();
			return data;
		}
		return null;
	}
	
	public DeptDTO getDeptId(int id) {
		dao = new DeptDAO();
		DeptDTO data = _getDeptId(id);
		dao.close();
		return data;
	}
	
	private DeptDTO _getDeptId(int id) {
		// public 메서드에서 dao 객체를 생성하게 해야함.
		DeptDTO data = dao.searchDeptId(id);
		return data;
	}

	public DEPT_SERVICE_STATUS addDept(DeptDTO data) {
		dao = new DeptDAO();
		DEPT_SERVICE_STATUS status = DEPT_SERVICE_STATUS.SUCCESS;
		
		if(_getDeptId(data.getDeptId()) != null) { // 객체가 있으면 중복이므로 DUPLICATED
			status = DEPT_SERVICE_STATUS.DEPT_ID_DUPLICATED;
		}
		if(!_existManager(data.getMngId())) { // 해당하는 값이 있으면 true이므로 !붙여서 false로 만들어서 통과하게한다, false로 오면 MNG_ID_NOT_EXISTS
			status = DEPT_SERVICE_STATUS.MNG_ID_NOT_EXISTS;
		}
		if(!_existLocation(data.getLocId())) { // 해당하는 값이 있으면 true이므로 !붙여서 false로 만들어서 통과하게한다, false로 오면 LOC_ID_NOT_EXISTS
			status = DEPT_SERVICE_STATUS.LOC_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.insertDept(data)) {
					dao.commit();
				} else {
					status = DEPT_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
			default:
				dao.close();
		}
		
		return status;
	}
	
	private boolean _existManager(int id) {
		boolean result = dao.existManager(id);
		return result;
	}
	
	private boolean _existLocation(int id) {
		boolean result = dao.existLocation(id);
		return result;
	}

	public DEPT_SERVICE_STATUS modifyDept(DeptDTO data) {
		// addDept 를 구현한 것과 유사하게 수정한 데이터에 대해
		// 문제가 발생한 경우 상세 오류를 구분할 수 있게 만들고
		// Controller 에 반환할 수 있게 한다.
		dao = new DeptDAO();
		DEPT_SERVICE_STATUS status = DEPT_SERVICE_STATUS.SUCCESS;
		
		if(!_existManager(data.getMngId())) {
			status = DEPT_SERVICE_STATUS.MNG_ID_NOT_EXISTS;
		}
		if(!_existLocation(data.getLocId())) {
			status = DEPT_SERVICE_STATUS.LOC_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.updateDept(data)) {
					dao.commit();
				} else {
					status = DEPT_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
			default:
				dao.close();
		}
		
		return status;
	}
}
