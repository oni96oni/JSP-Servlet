package comment.service;

import java.util.List;

import comment.model.CommentDAO;
import comment.model.CommentDTO;
import emps.model.EmpsDAO;

public class CommentService {

	public boolean add(CommentDTO data) {
		CommentDAO dao = new CommentDAO();
		
		boolean result = dao.insertData(data);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		
		return result;
	}

	public CommentDTO getData(int id) {
		CommentDAO dao = new CommentDAO();
		
		CommentDTO datas = dao.selectData(id);
		dao.close();
		
		return datas;
	}
	
	public List<CommentDTO> getDatas(int id) {
		CommentDAO dao = new CommentDAO();
		
		List<CommentDTO> datas = dao.selectDatas(id);
		dao.close();
		
		return datas;
	}

	public boolean removeId(int id) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.deleteId(id);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}

	public boolean remove(CommentDTO commentData) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.deleteData(commentData);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}

	public boolean modify(CommentDTO commentData) {
		CommentDAO dao = new CommentDAO();
		boolean result = dao.updateData(commentData);
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}
	
}