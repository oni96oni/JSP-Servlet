package emps.model;

public class EmpsDTO {
	private int empId;
	private String empName;
	private String email;
	private String jobName;
	private String jobId;
	private String deptName;
	private int deptId;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public void setEmpId(String empId) {
		this.empId = Integer.parseInt(empId);
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	
	@Override
	public String toString() {
		return "EmpsDTO [empId=" + empId + ", empName=" + empName + ", email=" + email + ", jobName=" + jobName
				+ ", jobId=" + jobId + ", deptName=" + deptName + ", deptId=" + deptId + "]";
	}
}