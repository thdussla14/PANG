package pangpang.model.Dto.car;

public class CarmanagementDto {
	private int carmanage_no;
	private String carmanage_number;
	private String carmanage_name;
	private String carmanage_img;
	private String carmanage_use_yn;
	private String carmanage_start;
	private String carmanage_finish;
	private int carmanage_cumulative_mileage;
	
	//빈생성자
	public CarmanagementDto() {
		super();
	}
	
	
	
	//풀생성자
	public CarmanagementDto(int carmanage_no, String carmanage_number, String carmanage_name, String carmanage_img,
			String carmanage_use_yn, String carmanage_start, String carmanage_finish,
			int carmanage_cumulative_mileage) {
		super();
		this.carmanage_no = carmanage_no;
		this.carmanage_number = carmanage_number;
		this.carmanage_name = carmanage_name;
		this.carmanage_img = carmanage_img;
		this.carmanage_use_yn = carmanage_use_yn;
		this.carmanage_start = carmanage_start;
		this.carmanage_finish = carmanage_finish;
		this.carmanage_cumulative_mileage = carmanage_cumulative_mileage;
	}
	//업로드 생성자
	public CarmanagementDto(String carmanage_number, String carmanage_name, String carmanage_img,
			String carmanage_use_yn, String carmanage_start, String carmanage_finish) {
		super();
		this.carmanage_number = carmanage_number;
		this.carmanage_name = carmanage_name;
		this.carmanage_img = carmanage_img;
		this.carmanage_use_yn = carmanage_use_yn;
		this.carmanage_start = carmanage_start;
		this.carmanage_finish = carmanage_finish;
	}
	
	//수정생성자
	public CarmanagementDto(int carmanage_no, String carmanage_img, String carmanage_use_yn, String carmanage_finish) {
		super();
		this.carmanage_no = carmanage_no;
		this.carmanage_img = carmanage_img;
		this.carmanage_use_yn = carmanage_use_yn;
		this.carmanage_finish = carmanage_finish;
	}
	
	
	public int getCarmanage_no() {
		return carmanage_no;
	}
	
	




	public void setCarmanage_no(int carmanage_no) {
		this.carmanage_no = carmanage_no;
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
	public String getCarmanage_use_yn() {
		return carmanage_use_yn;
	}
	public void setCarmanage_use_yn(String carmanage_use_yn) {
		this.carmanage_use_yn = carmanage_use_yn;
	}
	public String getCarmanage_start() {
		return carmanage_start;
	}
	public void setCarmanage_start(String carmanage_start) {
		this.carmanage_start = carmanage_start;
	}
	public String getCarmanage_finish() {
		return carmanage_finish;
	}
	public void setCarmanage_finish(String carmanage_finish) {
		this.carmanage_finish = carmanage_finish;
	}
	public int getCarmanage_cumulative_mileage() {
		return carmanage_cumulative_mileage;
	}
	public void setCarmanage_cumulative_mileage(int carmanage_cumulative_mileage) {
		this.carmanage_cumulative_mileage = carmanage_cumulative_mileage;
	}
	
	
	@Override
	public String toString() {
		return "CarmanagementDto [carmanage_no=" + carmanage_no + ", carmanage_number=" + carmanage_number
				+ ", carmanage_name=" + carmanage_name + ", carmanage_img=" + carmanage_img + ", carmanage_use_yn="
				+ carmanage_use_yn + ", carmanage_start=" + carmanage_start + ", carmanage_finish=" + carmanage_finish
				+ ", carmanage_cumulative_mileage=" + carmanage_cumulative_mileage + "]";
	}
	
	
}
