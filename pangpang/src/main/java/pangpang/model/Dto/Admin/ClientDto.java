package pangpang.model.Dto.Admin;

import javax.websocket.Session;

public class ClientDto {
	private Session session;
	private String member_id;
	private String member_name;
	private int member_no;
	
	public ClientDto() {
		// TODO Auto-generated constructor stub
	}

	

	public int getMember_no() {
		return member_no;
	}



	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}



	public ClientDto(Session session, String member_id, String member_name, int member_no) {
		super();
		this.session = session;
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_no = member_no;
	}



	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	@Override
	public String toString() {
		return "ClientDto [session=" + session + ", member_id=" + member_id + ", member_name=" + member_name
				+ ", member_no=" + member_no + "]";
	}

	
	
}
