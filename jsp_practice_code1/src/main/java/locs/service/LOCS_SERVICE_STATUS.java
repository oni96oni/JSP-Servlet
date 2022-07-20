package locs.service;

public enum LOCS_SERVICE_STATUS {
	SUCCESS(1, "성공"), FAILED(0, "실패"),
	LOC_ID_DUPLICATED(-1, "지역 ID 중복 오류"),
	STR_ADD_NOT_EXISTS(-2, "거리주소 존재하지 않음"),
	POS_CODE_NOT_EXISTS(-3, "우편번호 존재하지 않음"),
	CITY_ID_NOT_EXISTS(-4, "도시 존재하지 않음"),
	PRO_ID_NOT_EXISTS(-5, "지역 존재하지 않음"),
	CON_ID_NOT_EXISTS(-6, "국가 ID 존재하지 않음"),
	LOCS_ID_NOT_EXISTS(-7,"삭제할 데이터가 존재하지 않음");
	
	public final int value;
	public final String msg;
	
	LOCS_SERVICE_STATUS(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}
	
}