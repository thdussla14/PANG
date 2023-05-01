package pangpang.model.Dto.member;

public class MemberDto {
	private int member_no;
	private String member_name;
	private String member_id;
	private String member_pwd;
	private String member_email;
	private String member_phone;
	private String member_address;
	private String member_birth;
	private int member_rank;    
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}

	
	
	public MemberDto(int member_no, String member_name, String member_id, String member_pwd, String member_email,
			String member_phone, String member_address, String member_birth, int member_rank) {
		super();
		this.member_no = member_no;
		this.member_name = member_name;
		this.member_id = member_id;
		this.member_pwd = member_pwd;
		this.member_email = member_email;
		this.member_phone = member_phone;
		this.member_address = member_address;
		this.member_birth = member_birth;
		this.member_rank = member_rank;
	}
	

	public MemberDto(int member_no, String member_name, String member_id, String member_email, String member_phone,
			String member_address, String member_birth, int member_rank) {
		super();
		this.member_no = member_no;
		this.member_name = member_name;
		this.member_id = member_id;
		this.member_email = member_email;
		this.member_phone = member_phone;
		this.member_address = member_address;
		this.member_birth = member_birth;
		this.member_rank = member_rank;
	}



	@Override
	public String toString() {
		return "MemberDto [member_no=" + member_no + ", member_name=" + member_name + ", member_id=" + member_id
				+ ", member_pwd=" + member_pwd + ", member_email=" + member_email + ", member_phone=" + member_phone
				+ ", member_address=" + member_address + ", member_birth=" + member_birth + ", member_rank="
				+ member_rank + "]";
	}



	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pwd() {
		return member_pwd;
	}

	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getMember_birth() {
		return member_birth;
	}

	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}

	public int getMember_rank() {
		return member_rank;
	}

	public void setMember_rank(int member_rank) {
		this.member_rank = member_rank;
	}



	public String getMember_name() {
		return member_name;
	}



	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
}
