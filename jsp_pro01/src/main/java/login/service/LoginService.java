package login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import emps.model.EmpsDTO;
import login.model.LoginDAO;
import login.model.PermDTO;

public class LoginService {

	public boolean getLogin(HttpSession session, String empId, String deptId, String empName) {
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("empId", empId);
		mapData.put("deptId", deptId);
		mapData.put("firstName", "");
		mapData.put("lastName", "");
		
		String fullName[] = empName.split(" ", 2);
		if(fullName.length == 2) {
			mapData.put("firstName", fullName[0]);
			mapData.put("lastName", fullName[1]);
		}
		
		LoginDAO dao = new LoginDAO();
		EmpsDTO data = dao.selectLogin(mapData);
		
		if(data == null) {
			return false;
		} else {
			session.setAttribute("loginData", data);
			List<PermDTO> perm = dao.selectPerm(data.getEmpId());
			session.setAttribute("permData", perm);
			dao.close();
			return true;
		}
		
	}

}
