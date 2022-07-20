package locs.model;

public class LocsDTO {
	private int locId;
	private String stAddr;
	private String postal;
	private String city;
	private String state;
	private String ctyId;
	
	public int getLocId() {
		return locId;
	}
	
	public void setLocId(int locId) {
		this.locId = locId;
	}
	
	public String getStAddr() {
		return stAddr;
	}
	
	public void setStAddr(String stAddr) {
		this.stAddr = stAddr;
	}
	
	public String getPostal() {
		return postal;
	}
	
	public void setPostal(String postal) {
		this.postal = postal;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCtyId() {
		return ctyId;
	}
	
	public void setCtyId(String ctyId) {
		this.ctyId = ctyId;
	}
	
	@Override
	public String toString() {
		return "LocsDTO [locId=" + locId + ", stAddr=" + stAddr + ", postal=" + postal + ", city=" + city + ", state="
				+ state + ", ctyId=" + ctyId + "]";
	}
}
