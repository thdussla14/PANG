package pangpang.model.Dto.map;

public class ChartDto {
	
	private String date;
	private int count;
	
	public ChartDto() {
		// TODO Auto-generated constructor stub
	}

	public ChartDto(String date, int count) {
		super();
		this.date = date;
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
