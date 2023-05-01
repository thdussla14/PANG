package pangpang.model.Dto.car;

public class DrivecarDto {
	private int drivecar_no;
	private String drivecar_str_date;
	private String drivecar_end_date;
	private String drivecar_distance;
	private String drivecar_parking;
	private int bookcar_no;
	private String report_content;
	private String carmanage_img;
	private String carmanage_number;
	private String carmanage_name;
	private String member_name;
	//빈생성자
	public DrivecarDto() {
		super();
	}
	
	public DrivecarDto(int drivecar_no, String drivecar_str_date, String drivecar_end_date, String drivecar_distance,
			String drivecar_parking, int bookcar_no, String report_content, String carmanage_img,
			String carmanage_number, String carmanage_name, String member_name) {
		super();
		this.drivecar_no = drivecar_no;
		this.drivecar_str_date = drivecar_str_date;
		this.drivecar_end_date = drivecar_end_date;
		this.drivecar_distance = drivecar_distance;
		this.drivecar_parking = drivecar_parking;
		this.bookcar_no = bookcar_no;
		this.report_content = report_content;
		this.carmanage_img = carmanage_img;
		this.carmanage_number = carmanage_number;
		this.carmanage_name = carmanage_name;
		this.member_name = member_name;
	}

	//풀생성자
	public DrivecarDto(int drivecar_no, String drivecar_str_date, String drivecar_end_date, String drivecar_distance,
			String drivecar_parking, int bookcar_no) {
		super();
		this.drivecar_no = drivecar_no;
		this.drivecar_str_date = drivecar_str_date;
		this.drivecar_end_date = drivecar_end_date;
		this.drivecar_distance = drivecar_distance;
		this.drivecar_parking = drivecar_parking;
		this.bookcar_no = bookcar_no;
	}

	
	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public String getCarmanage_img() {
		return carmanage_img;
	}

	public void setCarmanage_img(String carmanage_img) {
		this.carmanage_img = carmanage_img;
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

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public DrivecarDto(int drivecar_no, String drivecar_str_date, String drivecar_end_date, String drivecar_distance,
			String drivecar_parking, int bookcar_no, String report_content) {
		super();
		this.drivecar_no = drivecar_no;
		this.drivecar_str_date = drivecar_str_date;
		this.drivecar_end_date = drivecar_end_date;
		this.drivecar_distance = drivecar_distance;
		this.drivecar_parking = drivecar_parking;
		this.bookcar_no = bookcar_no;
		this.report_content = report_content;
	}

	public int getDrivecar_no() {
		return drivecar_no;
	}

	public void setDrivecar_no(int drivecar_no) {
		this.drivecar_no = drivecar_no;
	}

	public String getDrivecar_str_date() {
		return drivecar_str_date;
	}

	public void setDrivecar_str_date(String drivecar_str_date) {
		this.drivecar_str_date = drivecar_str_date;
	}

	public String getDrivecar_end_date() {
		return drivecar_end_date;
	}

	public void setDrivecar_end_date(String drivecar_end_date) {
		this.drivecar_end_date = drivecar_end_date;
	}

	public String getDrivecar_distance() {
		return drivecar_distance;
	}

	public void setDrivecar_distance(String drivecar_distance) {
		this.drivecar_distance = drivecar_distance;
	}

	public String getDrivecar_parking() {
		return drivecar_parking;
	}

	public void setDrivecar_parking(String drivecar_parking) {
		this.drivecar_parking = drivecar_parking;
	}

	public int getBookcar_no() {
		return bookcar_no;
	}

	public void setBookcar_no(int bookcar_no) {
		this.bookcar_no = bookcar_no;
	}

	@Override
	public String toString() {
		return "DrivecarDto [drivecar_no=" + drivecar_no + ", drivecar_str_date=" + drivecar_str_date
				+ ", drivecar_end_date=" + drivecar_end_date + ", drivecar_distance=" + drivecar_distance
				+ ", drivecar_parking=" + drivecar_parking + ", bookcar_no=" + bookcar_no + "]";
	}
	
	
}
