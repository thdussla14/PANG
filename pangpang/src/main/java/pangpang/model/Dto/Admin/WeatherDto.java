package pangpang.model.Dto.Admin;

public class WeatherDto {
	
	private String current;
	private String summary;
	
	public WeatherDto() {
		
	}

	public WeatherDto(String current, String summary) {
		super();
		this.current = current;
		this.summary = summary;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
