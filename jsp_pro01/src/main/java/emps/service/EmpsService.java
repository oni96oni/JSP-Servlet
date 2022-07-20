package emps.service;

import java.util.ArrayList;
import java.util.List;

import emps.model.EmpsDAO;
import emps.model.EmpsDTO;
import emps.model.EmpsDetailDTO;

public class EmpsService {

	public List<EmpsDTO> getAll() {
		EmpsDAO dao = new EmpsDAO();
		List<EmpsDTO> datas = dao.selectAll();
		dao.close();
		
		return datas;
	}

	public List<EmpsDTO> getPage(int pageNumber, int count) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		EmpsDAO dao = new EmpsDAO();
		List<EmpsDTO> datas = dao.selectPage(start, end);
		dao.close();
		
		return datas;
	}
	
	public List<Integer> getPageNumberList(int count) {
		EmpsDAO dao = new EmpsDAO();
		int rowCount = dao.rowCount();
		dao.close();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int pageNum = (rowCount - 1) / count;
		for(int n = 0; n <= pageNum; n++) {
			pageList.add(n + 1);
		}
		return pageList;
	}
	
	public List<Integer> getPageNumberList() {
		return getPageNumberList(10);
	}

	public EmpsDetailDTO getEmpDetail(int empId) {
		EmpsDAO dao = new EmpsDAO();
		EmpsDetailDTO data = dao.selectEmpDetail(empId);
		dao.close();
		return data;
	}

	public boolean setEmp(EmpsDTO empsData, EmpsDetailDTO empsDetailData) {
		EmpsDAO dao = new EmpsDAO();
		
		boolean res1 = dao.updateEmp(empsData);
		boolean res2 = dao.updateEmpDetail(empsDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		} else {
			dao.rollback();
			dao.close();
			return false;
		}
		
	}

	public boolean add(EmpsDTO empsData, EmpsDetailDTO empsDetailData) {
		EmpsDAO dao = new EmpsDAO();
		
		boolean res1 = dao.insertEmp(empsData);
		boolean res2 = dao.updateEmpDetail(empsDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		} else {
			dao.rollback();
			dao.close();
			return false;
		}
		
	}

}
