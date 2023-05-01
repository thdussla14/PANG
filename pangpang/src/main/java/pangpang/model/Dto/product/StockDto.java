package pangpang.model.Dto.product;

public class StockDto {

	private int 	stockmanagementno;      
	private String 	stockmanagementdate;     
	private String 	stockmanagementenddate;                       
	private int 	stockmanagementtype;     
	private String 	stockmanagementcompany;   
	private int 	stockmanagementamount;   	
	private int 	product_price;       		                     
	private int 	product_no;
	
	// 추가
	private String 	product_name;   
	
	
	// 생성자
	// 빈생성자
	public StockDto() { }
	// 풀생성자
	
	public StockDto(int stockmanagementno, String stockmanagementdate, String stockmanagementenddate,
			int stockmanagementtype, String stockmanagementcompany, int stockmanagementamount, int product_price,
			int product_no) {
		super();
		this.stockmanagementno = stockmanagementno;
		this.stockmanagementdate = stockmanagementdate;
		this.stockmanagementenddate = stockmanagementenddate;
		this.stockmanagementtype = stockmanagementtype;
		this.stockmanagementcompany = stockmanagementcompany;
		this.stockmanagementamount = stockmanagementamount;
		this.product_price = product_price;
		this.product_no = product_no;
	}

	public StockDto(int stockmanagementno, String stockmanagementdate, String stockmanagementenddate,
			int stockmanagementtype, String stockmanagementcompany, int stockmanagementamount, int product_price,
			int product_no, String product_name) {
		super();
		this.stockmanagementno = stockmanagementno;
		this.stockmanagementdate = stockmanagementdate;
		this.stockmanagementenddate = stockmanagementenddate;
		this.stockmanagementtype = stockmanagementtype;
		this.stockmanagementcompany = stockmanagementcompany;
		this.stockmanagementamount = stockmanagementamount;
		this.product_price = product_price;
		this.product_no = product_no;
		this.product_name = product_name;
	}
	public StockDto(String stockmanagementdate, String stockmanagementenddate, int stockmanagementtype,
			String stockmanagementcompany, int stockmanagementamount, int product_price, int product_no) {
		super();
		this.stockmanagementdate = stockmanagementdate;
		this.stockmanagementenddate = stockmanagementenddate;
		this.stockmanagementtype = stockmanagementtype;
		this.stockmanagementcompany = stockmanagementcompany;
		this.stockmanagementamount = stockmanagementamount;
		this.product_price = product_price;
		this.product_no = product_no;
	}
	
	public StockDto( int product_no, String product_name,int stockmanagementamount) {
		super();		
		this.product_no = product_no;
		this.product_name = product_name;
		this.stockmanagementamount = stockmanagementamount;
	}
	

	@Override
	public String toString() {
		return "StockDto [stockmanagementno=" + stockmanagementno + ", stockmanagementdate=" + stockmanagementdate
				+ ", stockmanagementenddate=" + stockmanagementenddate + ", stockmanagementtype=" + stockmanagementtype
				+ ", stockmanagementcompany=" + stockmanagementcompany + ", stockmanagementamount="
				+ stockmanagementamount + ", product_price=" + product_price + ", product_no=" + product_no
				+ ", product_name=" + product_name + "]";
	}
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getStockmanagementno() {
		return stockmanagementno;
	}

	public void setStockmanagementno(int stockmanagementno) {
		this.stockmanagementno = stockmanagementno;
	}

	public String getStockmanagementdate() {
		return stockmanagementdate;
	}

	public void setStockmanagementdate(String stockmanagementdate) {
		this.stockmanagementdate = stockmanagementdate;
	}

	public String getStockmanagementenddate() {
		return stockmanagementenddate;
	}

	public void setStockmanagementenddate(String stockmanagementenddate) {
		this.stockmanagementenddate = stockmanagementenddate;
	}

	public int getStockmanagementtype() {
		return stockmanagementtype;
	}

	public void setStockmanagementtype(int stockmanagementtype) {
		this.stockmanagementtype = stockmanagementtype;
	}

	public String getStockmanagementcompany() {
		return stockmanagementcompany;
	}

	public void setStockmanagementcompany(String stockmanagementcompany) {
		this.stockmanagementcompany = stockmanagementcompany;
	}

	public int getStockmanagementamount() {
		return stockmanagementamount;
	}

	public void setStockmanagementamount(int stockmanagementamount) {
		this.stockmanagementamount = stockmanagementamount;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	
	
	
	
	
}
