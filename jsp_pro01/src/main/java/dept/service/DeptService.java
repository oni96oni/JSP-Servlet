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

	public DeptDTO getDeptId(String id) {
		/*
        이런 방식 대신 정규표현식으로 필터링한다.  

        여기서 id가 숫자로만 이루어져 있으면 진행, 아닌 경우에는 되돌리기 or Exception page보여주기

		int num = 0;
        for (int i = 0; i < id.length(); i++) {
        	if ('0' <= id.charAt(i) && id.charAt(i) <= '9') {
                num = num * 10 + (id.charAt(i) - '0');
            } else {
                System.out.println("숫자로만 입력하세요");
                return null;
            }
        }
		 */
		boolean isNumber = id.matches("\\d+");
		if(isNumber) {
			int deptId = Integer.parseInt(id);
			return _getDeptId(deptId);
		}
		return null;
	}

	public DeptDTO getDeptId(int id) {
		return _getDeptId(id);
	}

	private DeptDTO _getDeptId(int id) {
		DeptDTO data = dao.searchDeptId(id);
		return data;
	}

	public int addDept(DeptDTO data) {
		DeptDTO deptData = _getDeptId(data.getDeptId());
		if(deptData == null) {
			boolean isSaved = dao.insertDept(data);
			if(isSaved) {
				return 1;
			}
		}
		return -1;
	}
}
