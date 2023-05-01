package pangpang.model.Dto.product;

import java.util.ArrayList;

public class OrderDto {

	private int 	ordermanagement_no; 
	private String 	ordermanagement_date;
	private int   	ordermanagement_state ;     	     
	private String 	ordermanagement_address ;   	        
	private int   	member_no ;    			                       
	private int  	orderdetaildno   ;        
	private int  	orderdetaildamount ;                 	
	private int  	orderdetaildprice  ;                      	                 
	private int   	product_no    ; 
	private int   	payment_no ;        
	private String  payment_date  ;  
	private String  payment_how   ;   
	private int  	payment_price  ; 
	  
	// 추가 
	private String 	member_id;
	private ArrayList<CartDto> list;
	
	
	// 생성자
	// 빈생성자
	public OrderDto() { }
	// 풀생성자
	public OrderDto(int ordermanagement_no, String ordermanagement_date, int ordermanagement_state,
			String ordermanagement_address, int member_no, int orderdetaildno, int orderdetaildamount,
			int orderdetaildprice, int product_no, int payment_no, String payment_date, String payment_how,
			int payment_price, String member_id, ArrayList<CartDto> list) {
		super();
		this.ordermanagement_no = ordermanagement_no;
		this.ordermanagement_date = ordermanagement_date;
		this.ordermanagement_state = ordermanagement_state;
		this.ordermanagement_address = ordermanagement_address;
		this.member_no = member_no;
		this.orderdetaildno = orderdetaildno;
		this.orderdetaildamount = orderdetaildamount;
		this.orderdetaildprice = orderdetaildprice;
		this.product_no = product_no;
		this.payment_no = payment_no;
		this.payment_date = payment_date;
		this.payment_how = payment_how;
		this.payment_price = payment_price;
		this.member_id = member_id;
		this.list = list;
	}

	// 주문 등록
	public OrderDto(String ordermanagement_address, int member_no, String payment_how,
			int payment_price, ArrayList<CartDto> list) {
		super();
		this.ordermanagement_address = ordermanagement_address;
		this.member_no = member_no;
		this.payment_how = payment_how;
		this.payment_price = payment_price;
		this.list = list;
	}
	// 결제 
	public OrderDto(int ordermanagement_no, String ordermanagement_date, int ordermanagement_state, String payment_how,
			int payment_price) {
		super();
		this.ordermanagement_no = ordermanagement_no;
		this.ordermanagement_date = ordermanagement_date;
		this.ordermanagement_state = ordermanagement_state;
		this.payment_how = payment_how;
		this.payment_price = payment_price;
	}
	
	// 주문 출력
	public OrderDto(int ordermanagement_no, String ordermanagement_date, int ordermanagement_state,
			String ordermanagement_address, int member_no, String payment_date, String payment_how, int payment_price,
			String member_id, ArrayList<CartDto> list) {
		super();
		this.ordermanagement_no = ordermanagement_no;
		this.ordermanagement_date = ordermanagement_date;
		this.ordermanagement_state = ordermanagement_state;
		this.ordermanagement_address = ordermanagement_address;
		this.member_no = member_no;
		this.member_id = member_id;
		this.list = list;
	}
	
	// 주문 상세 출력
	public OrderDto(int ordermanagement_no, String payment_date, String payment_how, int payment_price,
			ArrayList<CartDto> list) {
		super();
		this.ordermanagement_no = ordermanagement_no;
		this.payment_date = payment_date;
		this.payment_how = payment_how;
		this.payment_price = payment_price;
		this.list = list;
	}	
	
	
	@Override
	public String toString() {
		return "OrderDto [ordermanagement_no=" + ordermanagement_no + ", ordermanagement_date=" + ordermanagement_date
				+ ", ordermanagement_state=" + ordermanagement_state + ", ordermanagement_address="
				+ ordermanagement_address + ", member_no=" + member_no + ", orderdetaildno=" + orderdetaildno
				+ ", orderdetaildamount=" + orderdetaildamount + ", orderdetaildprice=" + orderdetaildprice
				+ ", product_no=" + product_no + ", member_id=" + member_id + "]";
	}


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

	public int getOrderdetaildno() {
		return orderdetaildno;
	}

	public void setOrderdetaildno(int orderdetaildno) {
		this.orderdetaildno = orderdetaildno;
	}

	public int getOrderdetaildamount() {
		return orderdetaildamount;
	}

	public void setOrderdetaildamount(int orderdetaildamount) {
		this.orderdetaildamount = orderdetaildamount;
	}

	public int getOrderdetaildprice() {
		return orderdetaildprice;
	}

	public void setOrderdetaildprice(int orderdetaildprice) {
		this.orderdetaildprice = orderdetaildprice;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getPayment_no() {
		return payment_no;
	}

	public void setPayment_no(int payment_no) {
		this.payment_no = payment_no;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getPayment_how() {
		return payment_how;
	}

	public void setPayment_how(String payment_how) {
		this.payment_how = payment_how;
	}

	public int getPayment_price() {
		return payment_price;
	}

	public void setPayment_price(int payment_price) {
		this.payment_price = payment_price;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public ArrayList<CartDto> getList() {
		return list;
	}

	public void setList(ArrayList<CartDto> list) {
		this.list = list;
	}


	




	  
	
}
