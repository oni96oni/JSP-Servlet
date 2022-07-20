package emps.service;

public enum EMPS_SERVICE_STATUS {
	SUCCESS(1, "성공"), FAILED(0, "실패"),
	EMP_ID_DUPLICATED(-1, "직원 ID 중복 오류"),
	EMP_NAME_NOT_EXISTS(-2, "직원 이름 존재하지 않음"),
	EMP_EMAIL_NOT_EXISTS(-3, "이메일 존재하지 않음"),
	JOB_ID_NOT_EXISTS(-4, "직업 ID 존재하지 않음"),
	JOB_NAME_NOT_EXISTS(-5, "직업 이름 존재하지 않음"),
	DEPT_NAME_NOT_EXISTS(-6, "부서 이름 존재하지 않음"),
	DEPT_ID_NOT_EXISTS(-7, "부서 ID 존재하지 않음"),
	EMP_ID_NOT_EXISTS(-8, "직원 ID 존재하지 않음");
	
	public final int value;
	public final String msg;
	
	EMPS_SERVICE_STATUS(int value, String msg) {
		this.value = value;
		this.msg = msg;
	}
	
}
