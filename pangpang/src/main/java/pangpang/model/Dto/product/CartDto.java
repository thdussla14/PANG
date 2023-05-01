package pangpang.model.Dto.product;

public class CartDto {

	private int 		cart_no ;
	private int 		cart_amount	;			
	private int 		product_no;          	
	private int 		member_no ;        		
	
	// 추가
	private String 		product_name ;   
	private String   	product_option ; 
	private String   	product_unit  ;    
	private String  	product_img   ; 
	private int 		product_price;
	private int 		product_discount;	
	private String   	member_id ;    
	private int			stock;

	// 생성자
	// 빈생성자
	public CartDto() { }
	
	// 풀생성자
	public CartDto(int cart_no, int cart_amount, int product_no, int member_no, String product_name,
			String product_option, String product_unit, String product_img, int product_price, int product_discount,
			String member_id, int stock) {
		super();
		this.cart_no = cart_no;
		this.cart_amount = cart_amount;
		this.product_no = product_no;
		this.member_no = member_no;
		this.product_name = product_name;
		this.product_option = product_option;
		this.product_unit = product_unit;
		this.product_img = product_img;
		this.product_price = product_price;
		this.product_discount = product_discount;
		this.member_id = member_id;
		this.stock = stock;
	}

	// 등록용
	public CartDto(int cart_amount, int product_no, int member_no) {
		super();
		this.cart_amount = cart_amount;
		this.product_no = product_no;
		this.member_no = member_no;
	}

	// 출력용
	public CartDto(int cart_amount, int product_no, String product_name, String product_option, String product_unit,
			String product_img, int product_price) {
		super();
		this.cart_amount = cart_amount;
		this.product_no = product_no;
		this.product_name = product_name;
		this.product_option = product_option;
		this.product_unit = product_unit;
		this.product_img = product_img;
		this.product_price = product_price;
	}

	@Override
	public String toString() {
		return "CartDto [cart_no=" + cart_no + ", cart_amount=" + cart_amount + ", product_no=" + product_no
				+ ", member_no=" + member_no + ", product_name=" + product_name + ", product_option=" + product_option
				+ ", product_unit=" + product_unit + ", product_img=" + product_img + ", product_price=" + product_price
				+ ", product_discount=" + product_discount + ", member_id=" + member_id + "]";
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getCart_amount() {
		return cart_amount;
	}

	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
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

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	
	
}
