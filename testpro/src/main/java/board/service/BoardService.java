package board.service;

import board.model.Board;
import board.model.BoardDAO;

public class BoardService {
	private BoardDAO dao;
	
	public boolean insertBoard(Board data) {
		if(dao.insertBoard(data)) {
			dao.commit();
			dao.close();
			return true;
		} else {
			dao.rollback();
			dao.close();
			return false;
		}
	}
	
	public boolean updateBoard(Board data) {
		if(dao.updateBoard(data)) {
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
