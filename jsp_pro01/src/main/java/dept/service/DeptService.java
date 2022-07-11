package dept.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
	
	public List<DeptDTO> getPage(int pageNumber) {
		int start = (pageNumber - 1) * 10 + 1;
		int end = start + 9;
		
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<DeptDTO> getPage(int pageNumber, int count) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<Integer> getPageNumberList() {
		dao = new DeptDAO();
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
		dao = new DeptDAO();
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
	
	public List<DeptDTO> getSortPage(int pageNumber, int count, String sort) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		dao = new DeptDAO();
		
		System.out.println("getSortPage" + sort);
//		if(sort.equals("deptId")) {
//			sort="DEPARTMENT_ID";
//		} else if (sort.equals("deptName")) {
//			sort="DEPARTMENT_NAME";
//		} else if (sort.equals("mngId")) {
//			sort="MANAGER_ID";
//		} else {
//			sort="LOCATION_ID";
//		}
		System.out.println("getSortPage" + sort);
		List<DeptDTO> datas = dao.sortPage(start, end, sort);
		System.out.println("dao.sortPage(start, end, sort) 실행");
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
		
		if(_getDeptId(data.getDeptId()) != null) {
			status = DEPT_SERVICE_STATUS.DEPT_ID_DUPLICATED;
		}
		if(!_existManager(data.getMngId())) {
			status = DEPT_SERVICE_STATUS.MNG_ID_NOT_EXISTS;
		}
		if(!_existLocation(data.getLocId())) {
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

	public DEPT_SERVICE_STATUS deleteDept(String id) {
		DEPT_SERVICE_STATUS status = DEPT_SERVICE_STATUS.SUCCESS;
		
		dao = new DeptDAO();
		
		if(_getDeptId(Integer.parseInt(id)) == null) {
			status = DEPT_SERVICE_STATUS.DEPT_ID_NOT_EXISTS;
		}
		
		boolean result = dao.deleteDept(Integer.parseInt(id));
		if(result) {
			dao.commit();
		} else {
			status = DEPT_SERVICE_STATUS.FAILED;
			dao.rollback();
		}
		
		dao.close();
		return status;
	}
}
