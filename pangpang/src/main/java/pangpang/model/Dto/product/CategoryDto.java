package pangpang.model.Dto.product;

public class CategoryDto {

	private int			category_no;      
	private String  	category_name;     
	private String     	category_img ;
	
	public CategoryDto() { }

	public CategoryDto(int category_no, String category_name, String category_img) {
		super();
		this.category_no = category_no;
		this.category_name = category_name;
		this.category_img = category_img;
	}

	@Override
	public String toString() {
		return "CategoryDto [category_no=" + category_no + ", category_name=" + category_name + ", category_img="
				+ category_img + "]";
	}

	public int getCategory_no() {
		return category_no;
	}

	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_img() {
		return category_img;
	}

	public void setCategory_img(String category_img) {
		this.category_img = category_img;
	}
	
}
