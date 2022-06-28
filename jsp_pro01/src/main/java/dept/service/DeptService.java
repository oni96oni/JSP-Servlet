package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	
	private DeptDAO dao;
	
	public DeptService() {
		dao = new DeptDAO();
	}
	
	public List<DeptDTO> getAll() {
		//기능 동작을 구현한다.
		List<DeptDTO> datas = dao.searchAll();
		
		return datas;
	}
}