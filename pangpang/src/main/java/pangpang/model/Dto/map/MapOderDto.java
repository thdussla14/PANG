package pangpang.model.Dto.map;

public class MapOderDto {

	// 1. 필드
	private int ordermanagement_no;
	private String ordermanagement_date;
	private int ordermanagement_state;
	private String ordermanagement_address;
	private int member_no;
	
	// 2. 생성자
	public MapOderDto() {
		// TODO Auto-generated constructor stub
	}

	public MapOderDto(int ordermanagement_no, String ordermanagement_date, int ordermanagement_state,
			String ordermanagement_address, int member_no) {
		super();
		this.ordermanagement_no = ordermanagement_no;
		this.ordermanagement_date = ordermanagement_date;
		this.ordermanagement_state = ordermanagement_state;
		this.ordermanagement_address = ordermanagement_address;
		this.member_no = member_no;
	}
	
	
	
	
	// 3. 메소드

	public int getOrdermanagement_no() {
		return ordermanagement_no;
	}

	public void setOrdermanagement_no(int ordermanagement_no) {
		this.ordermanagement_no = ordermanagement_no;
	}

	public String getOrdermanagement_date() {
		return ordermanagement_date;
	}

	public void setOrdermanagement_date(String ordermanagement_date) {
		this.ordermanagement_date = ordermanagement_date;
	}

	public int getOrdermanagement_state() {
		return ordermanagement_state;
	}

	public void setOrdermanagement_state(int ordermanagement_state) {
		this.ordermanagement_state = ordermanagement_state;
	}

	public String getOrdermanagement_address() {
		return ordermanagement_address;
	}

	public void setOrdermanagement_address(String ordermanagement_address) {
		this.ordermanagement_address = ordermanagement_address;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	
	@Override
	public String toString() {
		return "MapOderDto [ordermanagement_no=" + ordermanagement_no + ", ordermanagement_date=" + ordermanagement_date
				+ ", ordermanagement_state=" + ordermanagement_state + ", ordermanagement_address="
				+ ordermanagement_address + ", member_no=" + member_no + "]";
	}
	
}
