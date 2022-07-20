package job.model;

public class JobDTO {

	private String JobId;
	private String JobName;
	private int minSalary;
	private int maxSalary;
	public String getJobId;
	
	public String getJobId() {
		return JobId;
	}
	
	public void setJobId(String jobId) {
		JobId = jobId;
	}
	
	public String getJobName() {
		return JobName;
	}
	
	public void setJobName(String jobName) {
		JobName = jobName;
	}
	
	public int getMinSalary() {
		return minSalary;
	}
	
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	
	public int getMaxSalary() {
		return maxSalary;
	}
	
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	@Override
	public String toString() {
		return "JobDTO [JobId=" + JobId + ", JobName=" + JobName + ", minSalary=" + minSalary + ", maxSalary="
				+ maxSalary + "]";
	}
}
