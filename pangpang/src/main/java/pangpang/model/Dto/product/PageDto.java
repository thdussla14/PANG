package pangpang.model.Dto.product;

import java.util.ArrayList;

public class PageDto {
	private int page; 		// 현재 페이지 
	private int listsize ; 	// 페이지당 게시물을 표시할 개수
	private int startrow; 	// 현재 페이지에서 시작되는 게시물 번호 
	private int totalsize; 	// 총 게시물 수
	private int totalpage; 	// 총 페이지 수 	
	private int btnsize ; 	// 최대 페이지버튼 출력수 
	private int startbtn;	  
	private int endbtn;	
		
	
	private ArrayList<OrderDto>  	orderList;	// 주문 목록 
	private ArrayList<ProductDto>   itemList;	// 제품 목록 
	private ArrayList<StockDto>   stockList;	// 재고 목록 
	
	public PageDto() { }

	public PageDto(int page, int listsize, int startrow, int totalsize, int totalpage, int btnsize, int startbtn,
			int endbtn, ArrayList<OrderDto> orderList) {
		super();
		this.page = page;
		this.listsize = listsize;
		this.startrow = startrow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnsize = btnsize;
		this.startbtn = startbtn;
		this.endbtn = endbtn;
		this.orderList = orderList;
	}

	public PageDto(ArrayList<ProductDto> itemList, int page, int listsize, int startrow, int totalsize, int totalpage, int btnsize, int startbtn,
			int endbtn) {
		super();
		this.page = page;
		this.listsize = listsize;
		this.startrow = startrow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnsize = btnsize;
		this.startbtn = startbtn;
		this.endbtn = endbtn;
		this.itemList = itemList;
	}

	public PageDto(int page, int listsize, int startrow, int totalsize, int totalpage, ArrayList<StockDto> stockList ,int btnsize, int startbtn,
			int endbtn) {
		super();
		this.page = page;
		this.listsize = listsize;
		this.startrow = startrow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnsize = btnsize;
		this.startbtn = startbtn;
		this.endbtn = endbtn;
		this.stockList = stockList;
	}

	@Override
	public String toString() {
		return "PageDto [page=" + page + ", listsize=" + listsize + ", startrow=" + startrow + ", totalsize="
				+ totalsize + ", totalpage=" + totalpage + ", btnsize=" + btnsize + ", startbtn=" + startbtn
				+ ", endbtn=" + endbtn + ", orderList=" + orderList + ", itemList=" + itemList + ", stockList="
				+ stockList + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getBtnsize() {
		return btnsize;
	}

	public void setBtnsize(int btnsize) {
		this.btnsize = btnsize;
	}

	public int getStartbtn() {
		return startbtn;
	}

	public void setStartbtn(int startbtn) {
		this.startbtn = startbtn;
	}

	public int getEndbtn() {
		return endbtn;
	}

	public void setEndbtn(int endbtn) {
		this.endbtn = endbtn;
	}

	public ArrayList<OrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderDto> orderList) {
		this.orderList = orderList;
	}

	public ArrayList<ProductDto> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<ProductDto> itemList) {
		this.itemList = itemList;
	}

	public ArrayList<StockDto> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<StockDto> stockList) {
		this.stockList = stockList;
	}
	
	
	
	
}
