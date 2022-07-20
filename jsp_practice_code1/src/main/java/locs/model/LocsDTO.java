package locs.model;

public class LocsDTO {
	
	private int locsId;
	private String strAdd;
	private String posCode;
	private String city;
	private String staPro;
	private String conId;
	
	
	public int getLocsId() {
		return locsId;
	}
	public void setLocsId(int locsId) {
		this.locsId = locsId;
	}
	public String getStrAdd() {
		return strAdd;
	}
	public void setStrAdd(String strAdd) {
		this.strAdd = strAdd;
	}
	public String getPosCode() {
		return posCode;
	}
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStaPro() {
		return staPro;
	}
	public void setStaPro(String staPro) {
		this.staPro = staPro;
	}
	public String getConId() {
		return conId;
	}
	public void setConId(String conId) {
		this.conId = conId;
	}
	
	
	@Override
	public String toString() {
		return "LocsDTO [locsId=" + locsId + ", strAdd=" + strAdd + ", posCode=" + posCode + ", city=" + city
				+ ", staPro=" + staPro + ", conId=" + conId + "]";
	}
}