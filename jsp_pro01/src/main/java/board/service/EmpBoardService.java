package board.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStaticsDTO;
import emps.model.EmpsDTO;

public class EmpBoardService {
	
	public int add(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		int seq = dao.getNextSeq();
		data.setId(seq);
		
		boolean result = dao.insertData(data);
		
		if(result) {
			dao.commit();
			dao.close();
			return data.getId();
		}
		
		dao.rollback();
		dao.close();
		return -1;
	}

	public EmpBoardDTO getData(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		EmpBoardDTO data = dao.selectData(id);
		dao.close();
		
		return data;
	}

	public void incViewCnt(HttpSession session, EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		// 조회카운트를 늘리기 전에 조회를 했었던 기록이 있는지 확인
		EmpBoardStaticsDTO staticsData = new EmpBoardStaticsDTO();
		staticsData.setbId(data.getId());
		staticsData.setEmpId(((EmpsDTO)session.getAttribute("loginData")).getEmpId());
		
		// 못찾으면 null이 되고 찾으면 객체 집어넣으니까 위의 set들과는 상관이 없다!
		staticsData = dao.selectStatics(staticsData);
		boolean result = false;
		
		// 접속했던 기록이 없으면 기록 남기기
		if(staticsData == null) {
			result = dao.updateViewCnt(data);
			
			staticsData = new EmpBoardStaticsDTO();
			staticsData.setbId(data.getId());
			staticsData.setEmpId(((EmpsDTO)session.getAttribute("loginData")).getEmpId());
			
			dao.insertStatics(staticsData);
		} else {
			// 기록이 있으면 작성한지 7일이 지났으면 조회수를 올려준다.
			long timeDiff = new Date().getTime() - staticsData.getLatestViewDate().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) {
				result = dao.updateViewCnt(data);
				dao.updateStatics(staticsData);
			}
		}
		
		if(result) {
			data.setViewCnt(data.getViewCnt() + 1);
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
	}

	public void incLike(HttpSession session, EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		EmpsDTO empData = (EmpsDTO) session.getAttribute("loginData");
		
		EmpBoardStaticsDTO staticsData = new EmpBoardStaticsDTO();
		staticsData.setbId(data.getId());
		staticsData.setEmpId(empData.getEmpId());
		
		staticsData = dao.selectStatics(staticsData);
		
		// 이전에 추천을 했는지 안 했는지 확인
		if(staticsData.isLiked()) {
			// 이전에 추천을 한 기록이 있으면 -> 추천 취소로 전환
			staticsData.setLiked(false);
			data.setLike(data.getLike() - 1);
			
		} else {
			// 이전에 추천을 한 기록이 없으면 -> 추천으로 전환
			staticsData.setLiked(true);
			data.setLike(data.getLike() + 1);
		}
		
		//위의 결과를 바탕으로 추천수 업데이트
		dao.updateStaticsLike(staticsData);
		
		boolean result = dao.updateLike(data);
		
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
	}
	
	public List<EmpBoardDTO> getAll() {
		EmpBoardDAO dao = new EmpBoardDAO();
		List<EmpBoardDTO> datas = dao.selectAll();
		dao.close();
		return datas;
	}
}