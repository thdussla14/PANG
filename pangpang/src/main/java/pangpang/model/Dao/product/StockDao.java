package pangpang.model.Dao.product;


import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.product.StockDto;

public class StockDao extends Dao{

	//싱글톤
	private static StockDao dao= new StockDao();
	private StockDao() {}
	public static StockDao getInstance() {
		return dao;
	}	
	
	// 전체 재고내역 레코드 개수
	public int totalsize_stock(String key,String keyword) {
		String sql = "";
		if(key.equals("key") && keyword.equals("keyword")) {
			sql = "select count(*) from stockmanagement";
		}else {
			sql = "select count(*) from stockmanagement where "+key+" like '%"+keyword+"%' ;";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {return rs.getInt(1);}			
		}catch(Exception e) { System.out.println(e);}
		return 0;
	}	
	
	// 재고 내역 전부 출력
	public ArrayList<StockDto> getStockList(int type,String key,String keyword,int startrow,int listsize) {
		ArrayList<StockDto> list = new ArrayList<>();
		String sql = "";
		if(key.equals("key") && keyword.equals("keyword")) {
			if(type==0){
				sql = "select *from stockmanagement order by stockmanagementno desc limit "+startrow+","+listsize;
			}else {	// 1: 입고 / 2: 출고 /3 : 폐기
				sql = "select *from stockmanagement where stockmanagementtype = "+type+" order by stockmanagementno desc limit "+startrow+","+listsize;
			}			
		}else {
			sql = "select *from stockmanagement where "+key+" like '%"+keyword+"%'  order by stockmanagementno desc limit "+startrow+","+listsize;
		}

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); 
			while(rs.next()){
				StockDto dto = new StockDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				list.add(dto);
			}
			return list;
		}catch (Exception e) { System.out.println(e);}
		return null;	
	}

	// 입고 등록
	public boolean setStock(StockDto dto) {
		
		String sql = "insert into stockmanagement (stockmanagementdate , stockmanagementenddate , stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no) "
				+ "values (?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStockmanagementdate());
			ps.setString(2, dto.getStockmanagementenddate());
			ps.setInt(3, dto.getStockmanagementtype());
			ps.setString(4, dto.getStockmanagementcompany());
			ps.setInt(5, dto.getStockmanagementamount());
			ps.setInt(6, dto.getProduct_price());
			ps.setInt(7, dto.getProduct_no());
			int count = ps.executeUpdate();
			if(count == 1 ) {return true;}
		}catch (Exception e) { System.out.println(e);}
		return false;
	}
	// 재고 내역 삭제
	public boolean deleteStock(int stockmanagementno) {
		
		String sql = "delete from stockmanagement where stockmanagementno = "+stockmanagementno;
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if(count == 1 ) {return true;}
		}catch (Exception e) { System.out.println(e);}
		return false;
	}
	
	// 폐기 대상 출력 
	public ArrayList<StockDto> getDropList(String date, String date2) {
		ArrayList<StockDto> list = new ArrayList<>();
		String sql = "select product_no pno, (select product_name from product where product_no = pno) pname,"
				+ " ((select ifnull(sum(stockmanagementamount),0) from stockmanagement where stockmanagementtype = 1 and stockmanagementenddate < '"+date+"' and product_no = pno)"
				+ " +(select ifnull(sum(stockmanagementamount),0) from stockmanagement where stockmanagementtype = 2 and stockmanagementdate    < '"+date+"' and product_no = pno)"
				+ " +(select ifnull(sum(stockmanagementamount),0) from stockmanagement where stockmanagementtype = 3 and stockmanagementdate    < '"+date2+"' and product_no = pno)) dropamount"
				+ " from stockmanagement group by product_no;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(3) != 0) {
						StockDto dto = new StockDto(rs.getInt(1), rs.getString(2), rs.getInt(3));
						list.add(dto);
				}
			}
			return list;
		}catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	// 폐기 처리
	public boolean drop(ArrayList<StockDto>list) {
		String sql = "";
		try{
			for(StockDto d : list) {
				System.out.println(d);
				sql = "insert into stockmanagement (stockmanagementtype,stockmanagementcompany,stockmanagementamount,product_price,product_no) "
					+ "values (3, \"(주)이젠위생\" , -"+d.getStockmanagementamount()+",1000,"+d.getProduct_no()+")";
				
				ps = con.prepareStatement(sql);	
				ps.executeUpdate(sql);					
			}
		return true;
		}catch(Exception e) {System.out.println(e);}
		return false;
	}
}
