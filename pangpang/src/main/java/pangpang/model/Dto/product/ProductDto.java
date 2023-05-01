package pangpang.model.Dto.product;

public class ProductDto {

	private int  		product_no;        
	private String   	product_name ;      
	private String   	product_option ;        
	private String   	product_unit  ;    
	private String  	product_img   ;      
	private String   	product_content ;   
	private int 		product_price;
	private int 		product_discount;
	private int   		category_no    ;   
	
	// 추가 
	private String   	category_name ;
	private int   		product_count  ; 	// 잔여 재고 수량  
	
	// 생성자
	// 빈생성자
	public ProductDto() { }
	// 풀생성자
	public ProductDto(int product_no, String product_name, String product_option, String product_unit,
			String product_img, String product_content, int product_price, int product_discount, int category_no,
			String category_name, int product_count) {
		super();
		this.product_no = product_no;
		this.product_name = product_name;
		this.product_option = product_option;
		this.product_unit = product_unit;
		this.product_img = product_img;
		this.product_content = product_content;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.category_no = category_no;
		this.category_name = category_name;
		this.product_count = product_count;
	}

	// 등록용 생성자
	public ProductDto(String product_name, String product_option, String product_unit, String product_img,
			String product_content, int product_price, int product_discount, int category_no) {
		super();
		this.product_name = product_name;
		this.product_option = product_option;
		this.product_unit = product_unit;
		this.product_img = product_img;
		this.product_content = product_content;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.category_no = category_no;
	}
	// 수정용 생성자
	public ProductDto(int product_no, String product_name, String product_option, String product_unit,
			String product_img, String product_content, int product_price, int product_discount, int category_no) {
		super();
		this.product_no = product_no;
		this.product_name = product_name;
		this.product_option = product_option;
		this.product_unit = product_unit;
		this.product_img = product_img;
		this.product_content = product_content;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.category_no = category_no;
	}
	@Override
	public String toString() {
		return "ProductDto [product_no=" + product_no + ", product_name=" + product_name + ", product_option="
				+ product_option + ", product_unit=" + product_unit + ", product_img=" + product_img
				+ ", product_content=" + product_content + ", product_price=" + product_price + ", product_discount="
				+ product_discount + ", category_no=" + category_no + ", category_name=" + category_name
				+ ", product_count=" + product_count + "]";
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_option() {
		return product_option;
	}
	public void setProduct_option(String product_option) {
		this.product_option = product_option;
	}
	public String getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
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
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	

	
	
	
}
