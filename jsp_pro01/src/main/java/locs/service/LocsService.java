package locs.service;

import java.util.List;

import locs.model.LocsDAO;
import locs.model.LocsDTO;

public class LocsService {
	
	private LocsDAO dao;
	
	public LocsService() {
		dao = new LocsDAO();
	}
	
	public List<LocsDTO> getAll() {
		//기능 동작을 구현한다.
		List<LocsDTO> datas = dao.searchAll();
		return datas;
	}
	
	public LocsDTO getLocsId(String id) {
		boolean isNumber = id.matches("\\d+");
		if(isNumber) {
			int deptId = Integer.parseInt(id);
			return _getLocsId(deptId);
		}
		return null;
	}
	
	public LocsDTO getLocsId(int id) {
		return _getLocsId(id);
	}
	
	private LocsDTO _getLocsId(int id) {
		LocsDTO data = dao.searchLocsId(id);
		return data;
	}
}
