package pangpang.model.Dto.car;

public class BookcarDto {
	private int bookcar_no;
	private String bookcar_str_date;
	private String bookcar_end_date;
	private String bookcar_yn;
	private int carmanage_no;
	private int member_no;
	private String reason;
	private String carmanage_img;
	private String carmanage_number;
	private String member_name;
	
	//추가
	
	
	public BookcarDto(String reason) {
		super();
		this.reason = reason;
	}

	
	public BookcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int carmanage_no, int member_no, String reason, String carmanage_img, String carmanage_number) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.carmanage_no = carmanage_no;
		this.member_no = member_no;
		this.reason = reason;
		this.carmanage_img = carmanage_img;
		this.carmanage_number = carmanage_number;
	}

	//빈생성자
	public BookcarDto() {
		super();
	}

	//출력용
	public BookcarDto(int bookcar_no, String bookcar_yn, int carmanage_no, int member_no) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_yn = bookcar_yn;
		this.carmanage_no = carmanage_no;
		this.member_no = member_no;
	}


	
	public BookcarDto(int bookcar_no, String bookcar_yn) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_yn = bookcar_yn;
	}




	public BookcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int carmanage_no, int member_no, String reason, String carmanage_img, String carmanage_number,
			String member_name) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.carmanage_no = carmanage_no;
		this.member_no = member_no;
		this.reason = reason;
		this.carmanage_img = carmanage_img;
		this.carmanage_number = carmanage_number;
		this.member_name = member_name;
	}



	
	public BookcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int member_no, String reason) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.member_no = member_no;
		this.reason = reason;
	}





	public BookcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			String reason) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.reason = reason;
	}


	public BookcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int carmanage_no, int member_no, String reason) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.carmanage_no = carmanage_no;
		this.member_no = member_no;
		this.reason = reason;
	}


	public BookcarDto(int bookcar_no, String bookcar_str_date, String bookcar_end_date, String bookcar_yn,
			int member_no, String reason, String carmanage_number, String member_name) {
		super();
		this.bookcar_no = bookcar_no;
		this.bookcar_str_date = bookcar_str_date;
		this.bookcar_end_date = bookcar_end_date;
		this.bookcar_yn = bookcar_yn;
		this.member_no = member_no;
		this.reason = reason;
		this.carmanage_number = carmanage_number;
		this.member_name = member_name;
	}


	public String getCarmanage_number() {
		return carmanage_number;
	}



	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public void setCarmanage_number(String carmanage_number) {
		this.carmanage_number = carmanage_number;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getCarmanage_img() {
		return carmanage_img;
	}

	public void setCarmanage_img(String carmanage_img) {
		this.carmanage_img = carmanage_img;
	}

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

	@Override
	public String toString() {
		return "BookcarDto [bookcar_no=" + bookcar_no + ", bookcar_str_date=" + bookcar_str_date + ", bookcar_end_date="
				+ bookcar_end_date + ", bookcar_yn=" + bookcar_yn + ", carmanage_no=" + carmanage_no + ", member_no="
				+ member_no + "]";
	}
	
	
}
