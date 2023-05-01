package pangpang.model.Dao.product;

import java.sql.Statement;
import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.product.CategoryDto;
import pangpang.model.Dto.product.ProductDto;

public class ProductDao extends Dao{

	//싱글톤
	private static ProductDao dao= new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() {
		return dao;
	}	
	
	// 카테고리 전체 출력
	public ArrayList<CategoryDto> getCategory() {
		ArrayList<CategoryDto> clist = new ArrayList<>();
		String sql = "select * from category";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CategoryDto cdto = new CategoryDto(rs.getInt(1), rs.getString(2), rs.getString(3));
				clist.add(cdto);
			}
			return clist;
		}catch (Exception e) { System.out.println(e);}
		return null;		
	}
	// 총 제품수 
	public int totalsizeP(int cno ,String key, String keyword) {
		
		String sql = "";
		if( key.equals("key") &&keyword.equals("keyword")) {
			if(cno==0) {
				sql = "select count(*) from product ";
			}else {
				sql = "select count(*) where category_no = "+cno+" from product ";
			}			
		}else {
			sql = "select count(*) from product where "+key+" like '%"+keyword+"%' ";
		}

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {return rs.getInt(1);}
		}catch(Exception e) { System.out.println(e);}		
		return 0;
	}	
	// 제품 출력
	public ArrayList<ProductDto> getProductList(int type,int cno,String key,String keyword,int startrow,int listsize) {
		ArrayList<ProductDto> plist = new ArrayList<>();
		String sql = "";
		if(key.equals("key") && keyword.equals("keyword")) {
			if(type==1) {		// 전체 제품 출력
				sql = "select p.*, c.category_name, sum(s.stockmanagementamount) stock from product p, category c, stockmanagement s  "
						+ "where p.category_no = c.category_no and p.product_no = s.product_no group by s.product_no limit "+startrow+","+listsize;
			}else if(type==2) {	// 카테고리별 제품 출력
				sql = "select p.*, c.category_name, sum(s.stockmanagementamount) stock from product p, category c, stockmanagement s "
						+ "where p.category_no = c.category_no and p.product_no = s.product_no and p.category_no = "+cno+" group by s.product_no limit "+startrow+","+listsize;
			}			
		}else { // 검색된 제품 출력 
			sql = "select p.*, c.category_name, sum(s.stockmanagementamount) stock from product p ,category c , stockmanagement s "
					+ "where p.category_no = c.category_no and p.product_no = s.product_no and "+key+" like '%"+keyword+"%' group by s.product_no limit "+startrow+","+listsize;
		}
			
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); 
			while(rs.next()){
				ProductDto pdto = new ProductDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
										rs.getString(6), rs.getInt(7),rs.getInt(8),rs.getInt(9), rs.getString(10),rs.getInt(11));
				plist.add(pdto); 
			}
			return plist;
		}catch (Exception e) { System.out.println(e);}
		return null;		
	}
	
	// 제품 1개 출력
	public ProductDto getProduct(int pno) {
		String sql = "select p.*, c.category_name, sum(s.stockmanagementamount) stock from product p, category c, stockmanagement s  where p.category_no = c.category_no and p.product_no = s.product_no and p.product_no = "+pno+" group by product_no";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {					
				ProductDto pdto = new ProductDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7),rs.getInt(8),rs.getInt(9), rs.getString(10),rs.getInt(11));
				return pdto;
			}			
		}catch (Exception e) { System.out.println(e);}
		return null;		
	} 

	// 품목 등록
	public boolean item_register(ProductDto dto) {
		String sql = "insert into product (product_name,product_option,product_unit,product_img,product_content,product_price,product_discount,category_no) values (?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getProduct_name());
			ps.setString(2, dto.getProduct_option());
			ps.setString(3, dto.getProduct_unit());
			ps.setString(4, dto.getProduct_img());
			ps.setString(5, dto.getProduct_content());			
			ps.setInt(6, dto.getProduct_price());
			ps.setInt(7, dto.getProduct_discount());
			ps.setInt(8, dto.getCategory_no());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int pk = rs.getInt(1);
				if(pk != 0) {
					sql = "insert into stockmanagement (stockmanagementtype , stockmanagementcompany , stockmanagementamount  , product_price ,product_no) "
							+ "values (?,?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, 1);
					ps.setString(2, "PANGPANG");
					ps.setInt(3, 0);
					ps.setInt(4, 0);
					ps.setInt(5, pk);
					int count = ps.executeUpdate();
					if(count == 1 ) {return true;}
				}
			}		
		}catch (Exception e) {System.out.println(e);}		
		return false;
	}
	// 품목 수정
	public boolean item_update(ProductDto dto) {
		String sql = "update product set product_name = ? ,product_option = ? ,product_unit = ? ,product_img = ? ,product_content = ? ,product_price = ? ,product_discount = ? ,category_no = ? where product_no = ? ";
		try {
			ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getProduct_name());
			ps.setString(2, dto.getProduct_option());
			ps.setString(3, dto.getProduct_unit());
			ps.setString(4, dto.getProduct_img());
			ps.setString(5, dto.getProduct_content());
			ps.setInt(6, dto.getProduct_price());
			ps.setInt(7, dto.getProduct_discount());
			ps.setInt(8, dto.getCategory_no());
			ps.setInt(9, dto.getProduct_no());
			int count = ps.executeUpdate();
			if(count == 1 ) {return true;}
		}catch (Exception e) {System.out.println(e);}		
		return false;
	}
	// 품목 삭제
	public boolean item_delete(int pno) {
		String sql = "delete from product where product_no = "+pno;		
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if(count==1) {return true;}			
		}catch (Exception e) {System.out.println(e);}		
		return false;
	}
	
	
	
	
	
}