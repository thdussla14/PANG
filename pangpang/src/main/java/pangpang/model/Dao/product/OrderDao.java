package pangpang.model.Dao.product;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.product.CartDto;
import pangpang.model.Dto.product.OrderDto;
import pangpang.model.Dto.product.ProductDto;

public class OrderDao extends Dao{

	//싱글톤
	private static OrderDao dao= new OrderDao();
	private OrderDao() {}
	public static OrderDao getInstance() {
		return dao;
	}	
	
	// 장바구니 추가
	public int cartIn(int amount,int pno,int mno) {
		// 기존 장바구니 동일 제품 존재 확인
		String sql = "select c.*, p.* from cart c, product p where c.product_no = p.product_no and c.member_no = "+mno;	
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(3)==pno) {return 3;}
			}
			// 장바구니 추가
			sql = "insert into cart (cart_amount,product_no,member_no) values (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setInt(2, pno);
			ps.setInt(3, mno);
			int count = ps.executeUpdate();
			if(count==1) {return 1;}			
		}catch (Exception e) {System.out.println(e);}		
		return 2;
	}
	// 장바구니 출력 // 제품 출력
	public ArrayList<CartDto> printCart(int mno) {
		ArrayList<CartDto> list = new ArrayList<>(); 
		String sql = "select c.*, p.* from cart c, product p where c.product_no = p.product_no and c.member_no = "+mno;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				sql = "select sum(s.stockmanagementamount) stock from product p, stockmanagement s  where p.product_no = s.product_no and s.product_no = "+rs.getInt(3)+" group by s.product_no";
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				if(rs2.next()) {
					CartDto dto = new CartDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(6), 
							rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(11), rs.getInt(12), null, rs2.getInt(1));
					list.add(dto); System.out.println(dto);	
				}
			}
			return list;			
		}catch (Exception e) {System.out.println(e);}		
		return null;
	}
	// 장바구니 전체 취소
	public boolean cartOutAll(int mno) {
		String sql = "delete from cart where member_no = "+mno;		
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.executeUpdate();
			return true;			
		}catch (Exception e) {System.out.println(e);}		
		return false;
	}
	// 장바구니 취소
	public boolean cartOut(int pno,int mno) {
		String sql = "delete from cart where product_no = "+pno+" and member_no = "+mno;		
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if(count==1) {return true;}		
		}catch (Exception e) {System.out.println(e);}		
		return false;
	}
	
	// 전체 주문 레코드 개수
	public int totalsize_order(String key,String keyword) {
		String sql = "";
		if(key.equals("key") && keyword.equals("keyword")) {
			sql = "select count(*) from ordermanagement order by ordermanagement_date desc ;";
		}else {
			sql = "select count(*) from ordermanagement where "+key+" like '%"+keyword+"%' order by ordermanagement_date desc ;";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {return rs.getInt(1);}			
		}catch(Exception e) { System.out.println(e);}
		return 0;
	}
	
	// 주문 목록 출력
	public ArrayList<OrderDto> getOrderList(int type, int mno, String key,String keyword,int startrow,int listsize) {
		ArrayList<OrderDto> list = new ArrayList<>();
		
		String sql = "";
		if(key.equals("key") && keyword.equals("keyword")) {
			if(type==-1) { 		// -1: 로그인 회원 주문			
				sql = "select o.*, member_id from ordermanagement o natural join member where member_no = "+mno+" order by ordermanagement_no desc ";
			}else if(type==0) { //  0: 전체 주문
				sql = "select o.*, member_id from ordermanagement o natural join member order by ordermanagement_no desc limit "+startrow+","+listsize;
			}else{              //  1: 결제확인중/2:결제확인/3:배송지연/4:배송중/5:배송완료/6:거래완료/  
				sql = "select o.*, member_id from ordermanagement o natural join member where ordermanagement_state = "+type+" order by ordermanagement_no desc limit "+startrow+","+listsize;
			}
		}else { // 검색된 주문
			if(type==0) {
				sql = "select o.*, member_id from ordermanagement o natural join member where "+key+" like '%"+keyword+"%' order by ordermanagement_no desc limit "+startrow+","+listsize;
			}else {
				sql = "select o.*, member_id from ordermanagement o natural join member where "+key+" like '%"+keyword+"%' and ordermanagement_state = "+type+" order by ordermanagement_no desc limit "+startrow+","+listsize;	
			}			
		}

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); 
			while(rs.next()){
				OrderDto dto = new OrderDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), 
						rs.getInt(5), null,null, 0, rs.getString(6), null);
				list.add(dto);
			}
			return list;
		}catch (Exception e) { System.out.println(e);}
		return null;		
	}
	// 주문 상세 출력
	public OrderDto getOrderDetail(int ordermanagement_no) {
		ArrayList<CartDto> list = new ArrayList<>();
		String sql = "select * from payment where ordermanagement_no = "+ordermanagement_no;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				sql = "select * from orderdetail natural join product where ordermanagement_no = "+ordermanagement_no;
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();				
				while(rs2.next()) {
					CartDto cdto = new CartDto(rs2.getInt(3), rs2.getInt(1), rs2.getString(6), rs2.getString(7), 
							rs2.getString(8), rs2.getString(9), rs2.getInt(4));
					list.add(cdto);
				}				
				OrderDto dto = new OrderDto(ordermanagement_no,rs.getString(2),rs.getString(3), rs.getInt(4), list);
				return dto;
			}			
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	// 주문 내역 등록
	public boolean setOrder(OrderDto dto) {
		String sql = "insert into ordermanagement ( ordermanagement_address , member_no ) values (?,?)";
		try {
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getOrdermanagement_address());
			ps.setInt(2, dto.getMember_no());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int pk = rs.getInt(1); 		System.out.println(pk);
				// 주문 등록 후 주문상세 DB에 해당 주문 품목 list 입력
				if(pk != 0) {				
					ArrayList<CartDto> list =  dto.getList(); System.out.println(list);
					for(CartDto d : list) {
						System.out.println(d);
						sql = "insert into orderdetail ( orderdetaildamount , orderdetaildprice, ordermanagement_no , product_no ) values "
								+ "( "+d.getCart_amount()+","+d.getProduct_price()+","+pk+","+d.getProduct_no()+")";
						ps = con.prepareStatement(sql);	
						ps.executeUpdate(sql);					
					}
					// 결제 정보 등록
					sql = "insert into payment ( payment_how , payment_price, ordermanagement_no ) values "
								+ " ( '"+dto.getPayment_how()+"' ,"+dto.getPayment_price()+","+pk+" )";
					ps = con.prepareStatement(sql);	
					ps.executeUpdate();
					// 재고 차감					
					for(CartDto d : list) {
						sql = "insert into stockmanagement(stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no)"
								+ " values ( 2,"+d.getMember_no()+",-"+d.getCart_amount()+","+d.getProduct_price()+","+d.getProduct_no()+")";
						ps = con.prepareStatement(sql);	
						ps.executeUpdate(sql);					
					}
					return true;
				}
			}			
		}catch (Exception e) {System.out.println(e);}
		return false;
	}

	// 주문 상태 변경
	public boolean updateState(int state , int ordermanagement_no) {
		String sql = "update ordermanagement set ordermanagement_state = "+state+" where ordermanagement_no = "+ordermanagement_no;
		try {
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
}
