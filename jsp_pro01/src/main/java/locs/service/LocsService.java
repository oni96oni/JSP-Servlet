package locs.service;

import java.util.ArrayList;
import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;
import dept.service.DEPT_SERVICE_STATUS;
import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
	
	private LocsDAO dao;
	
	public List<LocsDTO> getAll() {
		//기능 동작을 구현한다.
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.searchAll();
		dao.close();
		return datas;
	}
	
	public List<LocsDTO> getPage(int pageNumber) {
		int start = (pageNumber - 1) * 10 + 1;
		int end = start + 9;
		
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<LocsDTO> getPage(int pageNumber, int count) {
		int start = (pageNumber - 1) * count + 1;
		int end = start + count - 1;
		
		dao = new LocsDAO();
		List<LocsDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	public List<Integer> getPageNumberList() {
		dao = new LocsDAO();
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
		dao = new LocsDAO();
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
	
	public LocsDTO getLocsId(String id) {
		boolean isNumber = id.matches("\\d+");
		if(isNumber) {
			int locsId = Integer.parseInt(id);
			dao = new LocsDAO();
			LocsDTO data = _getLocsId(locsId);
			dao.close();
			return data;
		}
		return null;
	}
	
	public LocsDTO getLocsId(int id) {
		dao = new LocsDAO();
		LocsDTO data = _getLocsId(id);
		dao.close();
		return data;
	}
	
	private LocsDTO _getLocsId(int id) {
		LocsDTO data = dao.searchLocsId(id);
		return data;
	}
	
	private boolean _existCountry(String conId) {
		boolean result = dao.existConId(conId);
		System.out.println("데이터접근성공");
		return result;
	}
	
	public LOCS_SERVICE_STATUS addLocs(LocsDTO data) {
		dao = new LocsDAO();
		LOCS_SERVICE_STATUS status = LOCS_SERVICE_STATUS.SUCCESS;
		
		if(_getLocsId(data.getLocsId()) != null) {
			status = LOCS_SERVICE_STATUS.LOC_ID_DUPLICATED;
		}
		if(_existCountry(data.getConId()) == false) {
			status = LOCS_SERVICE_STATUS.CON_ID_NOT_EXISTS;
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.insertLocs(data)) {
					dao.commit();
				} else {
					status = LOCS_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
			default:
				dao.close();
		}
		return status;
	}
	
	public LOCS_SERVICE_STATUS modifyLocs(LocsDTO data) {
		// addDept 를 구현한 것과 유사하게 수정한 데이터에 대해
		// 문제가 발생한 경우 상세 오류를 구분할 수 있게 만들고
		// Controller 에 반환할 수 있게 한다.
		dao = new LocsDAO();
		LOCS_SERVICE_STATUS status = LOCS_SERVICE_STATUS.SUCCESS;
		
		if(_getLocsId(data.getLocsId()) == null) {
			status = LOCS_SERVICE_STATUS.LOC_ID_DUPLICATED;
			System.out.println("LOC_ID_DUPLICATED");
		}
		if(!_existCountry(data.getConId())) {
			status = LOCS_SERVICE_STATUS.CON_ID_NOT_EXISTS;
			System.out.println("CON_ID_NOT_EXISTS");
		}
		
		switch(status) {
			case SUCCESS:
				if(dao.updateLocs(data)) {
					dao.commit();
				} else {
					status = LOCS_SERVICE_STATUS.FAILED;
					dao.rollback();
				}
			default:
				dao.close();
		}
		
		return status;
	}

	public LOCS_SERVICE_STATUS deleteLocs(String id) {
		LOCS_SERVICE_STATUS status = LOCS_SERVICE_STATUS.SUCCESS;
		
		dao = new LocsDAO();
		
		if(_getLocsId(Integer.parseInt(id)) == null) {
			status = LOCS_SERVICE_STATUS.LOCS_ID_NOT_EXISTS;
		}
		
		boolean result = dao.deleteLocs(Integer.parseInt(id));
		if(result) {
			dao.commit();
		} else {
			status = LOCS_SERVICE_STATUS.FAILED;
			dao.rollback();
		}
		
		dao.close();
		return status;
	}
}