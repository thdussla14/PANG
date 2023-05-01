package pangpang.model.Dto.map;

public class MapcarDto {
	
	// 1. 필드
	private int bookcar_no;
	private String bookcar_str_date; 
	private String bookcar_end_date;
	private String bookcar_yn;
	private int carmanage_no;
    private int member_no;
    // 추가필드
    private String reason;
    private String carmanage_number;
    private String carmanage_name;
    private String carmanage_img;
    
    
	// 2. 생성자
    public MapcarDto() {}

	public MapcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int carmanage_no, int member_no, String carmanage_number, String carmanage_name, String carmanage_img) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.carmanage_no = carmanage_no;
		this.member_no = member_no;
		this.carmanage_number = carmanage_number;
		this.carmanage_name = carmanage_name;
		this.carmanage_img = carmanage_img;
	}
	
	public MapcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int carmanage_no, int member_no, String reason, String carmanage_number, String carmanage_name,
			String carmanage_img) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.carmanage_no = carmanage_no;
		this.member_no = member_no;
		this.reason = reason;
		this.carmanage_number = carmanage_number;
		this.carmanage_name = carmanage_name;
		this.carmanage_img = carmanage_img;
	}

	
	// 3. 메소드
	public int getBookcar_no() {
		return bookcar_no;
	}

	public void setBookcar_no(int bookcar_no) {
		this.bookcar_no = bookcar_no;
	}

	public String getBookcar_str_date() {
		return bookcar_str_date;
	}

	public void setBookcar_str_date(String bookcar_str_date) {
		this.bookcar_str_date = bookcar_str_date;
	}

	public String getBookcar_end_date() {
		return bookcar_end_date;
	}

	public void setBookcar_end_date(String bookcar_end_date) {
		this.bookcar_end_date = bookcar_end_date;
	}

	public String getBookcar_yn() {
		return bookcar_yn;
	}

	public void setBookcar_yn(String bookcar_yn) {
		this.bookcar_yn = bookcar_yn;
	}

	public int getCarmanage_no() {
		return carmanage_no;
	}

	public void setCarmanage_no(int carmanage_no) {
		this.carmanage_no = carmanage_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getCarmanage_number() {
		return carmanage_number;
	}

	public void setCarmanage_number(String carmanage_number) {
		this.carmanage_number = carmanage_number;
	}

	public String getCarmanage_name() {
		return carmanage_name;
	}

	public void setCarmanage_name(String carmanage_name) {
		this.carmanage_name = carmanage_name;
	}

	public String getCarmanage_img() {
		return carmanage_img;
	}

	public void setCarmanage_img(String carmanage_img) {
		this.carmanage_img = carmanage_img;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "MapcarDto [bookcar_no=" + bookcar_no + ", bookcar_str_date=" + bookcar_str_date + ", bookcar_end_date="
				+ bookcar_end_date + ", bookcar_yn=" + bookcar_yn + ", carmanage_no=" + carmanage_no + ", member_no="
				+ member_no + ", carmanage_number=" + carmanage_number + ", carmanage_name=" + carmanage_name
				+ ", carmanage_img=" + carmanage_img + "]";
	}

}