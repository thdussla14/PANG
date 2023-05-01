package pangpang.model.Dto.member;

public class SaltDto {
	private String salt;
	private String sdate;
	
	public SaltDto() {
		// TODO Auto-generated constructor stub
	}

	public SaltDto(String salt, String sdate) {
		super();
		this.salt = salt;
		this.sdate = sdate;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "Salt [salt=" + salt + ", sdate=" + sdate + "]";
	}
	
}
