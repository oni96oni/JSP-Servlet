package board.model;

import java.util.Date;

public class EmpBoardDTO {
	
	private int id;
	private String title;
	private String content;
	private int empId;
	private Date createDate;
	
	
	public EmpBoardDTO() {}
	
	public EmpBoardDTO(int id, String title, String content, int empId, Date createDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.empId = empId;
		this.createDate = createDate;
	}
	
	
	@Override
	public String toString() {
		return "EmpBoardDTO [id=" + id + ", title=" + title + ", content=" + content + ", empId=" + empId
				+ ", createDate=" + createDate + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}