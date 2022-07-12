package emps.model;

import java.sql.Date;

public class EmpsDetailDTO {
	private int empId; //필수! 있어야지 특정 직원의 상세정보를 구할 수 있다.
	private Date hireDate;
	private String phone;
	private int salary;
	private double commission;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phonee) {
		this.phone = phonee;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	
	@Override
	public String toString() {
		return "EmpsDetailDTO [empId=" + empId + ", hireDate=" + hireDate + ", phonee=" + phone + ", salary=" + salary
				+ ", commission=" + commission + "]";
	}
}