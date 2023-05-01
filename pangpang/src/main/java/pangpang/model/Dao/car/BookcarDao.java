package pangpang.model.Dao.car;

import java.sql.Date;
import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.car.BookcarDto;
import pangpang.model.Dto.car.CarmanagementDto;

public class BookcarDao extends Dao{
		//싱글톤
		private static BookcarDao dao= new BookcarDao();
		private BookcarDao() {}
		public static BookcarDao getInstance() {
			return dao;
		}		
	 
		  
		   //배차관리에서 선택을위한 차량 출력 즉 첫화면 프론트용
		  public ArrayList<CarmanagementDto>carListy(){
		      ArrayList<CarmanagementDto>list = new ArrayList<>();
		      String sql="select*from carmanage where carmanage_use_yn=\"y\";";
		      try {
		         ps=con.prepareStatement(sql);
		         rs=ps.executeQuery();
		         
		         while(rs.next()) {
		      
		            CarmanagementDto dto = new CarmanagementDto(
		                  rs.getInt(1),
		                  rs.getString(2),
		                  rs.getString(3),
		                  rs.getString(4),
		                  rs.getString(5),
		                  rs.getString(6),
		                  rs.getString(7),
		                  rs.getInt(8));
		            list.add(dto);
		         }
		         
		         System.out.println("carList list ::: " + list);
		      }catch (Exception e) {
		            System.out.println("carmanagementDao의 전체출력"+e);
		      }
		      return list;
		   }
		  
		// 배차신청하기 전 배차예약시간 체크
		  public boolean bookCheck(int carmanage_no, String bookcar_str_date, String bookcar_end_date) {
			  
			  boolean result = true;
			  
			  /*
			  String sql = "SELECT * "
			  			 + "  FROM BOOKCAR"
			  			 + " WHERE CARMANAGE_NO = " + carmanage_no
			  			 + "   -- AND BOOKCAR_YN = 'Y'" // 배차승인 완료된 애들만 체크를 한다면 앞에 있는 '--' 를 빼주세요
			  			 + "   AND ((BOOKCAR_STR_DATE <= DATE(\""+ bookcar_str_date + "\") AND BOOKCAR_END_DATE >= DATE(\""+ bookcar_str_date + "\"))"
			  			 		+ "OR (BOOKCAR_STR_DATE <= DATE(\""+ bookcar_end_date + "\") AND BOOKCAR_END_DATE >= DATE(\""+ bookcar_end_date + "\")));";
			  */
			  
			  String sql = "select * from bookcar where carmanage_no = ? and ((bookcar_str_date between ? and ? ) or (bookcar_end_date between ? and ? ))";
			  
			  try {
				  System.out.println("bookCheck sql ::: " + sql);
				  
				  ps=con.prepareStatement(sql);
				  
				  ps.setInt(1, carmanage_no);
				  ps.setString(2, bookcar_str_date);
				  ps.setString(3, bookcar_end_date);
				  ps.setString(4, bookcar_str_date);
				  ps.setString(5, bookcar_end_date);
				  
			      rs = ps.executeQuery();
			      
			      if( rs.next() ) {
			    	  System.out.println(rs.getInt(1));
			    	  System.out.println(rs.getString(6));
			    	  result =  false;
			      }
				  
			  } catch (Exception e) {
				  System.out.println("BookcarDao.java bookCheck error 발생 ::: " + e);
			  }
			  
			  return result;
		  }
		  		
		// 배차예약정보를 최고권위자한테 줘야함
		public boolean book(int mno, int carmanage_no, String bookcar_destination, String bookcar_str_date, String bookcar_end_date) {
						
			String sql="insert into bookcar "
					+ "	(bookcar_str_date,bookcar_end_date,bookcar_yn,carmanage_no,member_no) "
					+ "		values (?,?,?,?,?);";
			
			boolean result = bookCheck(carmanage_no, bookcar_str_date, bookcar_end_date);
			
			System.out.println("book bookCheck result ::: " + result);
			
			try {
				
				if ( result ) {
					
					ps = con.prepareStatement(sql);
					ps.setString(1, bookcar_str_date);
					ps.setString(2, bookcar_end_date);
					ps.setString(3, null);
					ps.setInt(4, carmanage_no);
					ps.setInt(5, mno);
					
					ps.executeUpdate();
					
					System.out.println("BookcarDao.java book sql ::: " + sql);
					  return true;
					  
				} else {
					  return false;
				}
				
			
			}catch (Exception e) {
				System.out.println(e);
			}
			
			return false;
		}
		
		
		   //배차테이블 전체출력 한번 만들어보기만 함 (테스트용임)
		  public ArrayList<BookcarDto>bookcarlist(){
		      ArrayList<BookcarDto>list = new ArrayList<>();
		      String sql="select b.*, c.carmanage_img, c.carmanage_number, m.member_name from member m, carmanage c, bookcar b\r\n"
		      			+ "where c.carmanage_no =b.carmanage_no and m.member_no= b.member_no and bookcar_yn is null; ";
		      try {
		         ps=con.prepareStatement(sql);
		         rs=ps.executeQuery();
		         while(rs.next()) {
		        	 BookcarDto dto = new BookcarDto(
		        			 rs.getInt(1), 
		        			 rs.getString(2),
		        			 rs.getString(3), 
		        			 rs.getString(4),
		        			 rs.getInt(5),
		        			 rs.getInt(6),
		        			 rs.getString(7), 
		        			 rs.getString(8), 
		        			 rs.getString(9),
		        			 rs.getString(10)
		        			 );
		        	 list.add(dto);
		        	 };
		         System.out.println("bookcarlist list ::: " + list);
		      }catch (Exception e) {
		            System.out.println("BookcarDao의 전체출력"+e);
		      }
		      return list;
		   }
	

		  //배차이력승인시 db 업데이트해주는 로직
		  public void bookcarUpdate(String bookcar_yn, int bookcar_no,String reason) {
			  String sql = "UPDATE BOOKCAR SET BOOKCAR_YN = ? , reason = ? WHERE BOOKCAR_NO = ?";
			  
			  try {
				  	ps=con.prepareStatement(sql);
				  	ps.setString(1, bookcar_yn);
				  	ps.setString(2, reason);
				  	ps.setInt(3, bookcar_no);
				  	System.out.println("BookcarDao.java bookcarUpdate sql ::: " + sql);
				  	ps.executeUpdate();
					
				  
			} catch (Exception e) {
				System.out.println("bookcarUpdate의 전체출력"+e);
			}
			  
			 return ;
		  }
		

		  //배차이력승인시 db 업데이트해주는 로직
		  public void bookcarUpdate2(String bookcar_yn, int bookcar_no) {
			  String sql = "UPDATE BOOKCAR SET BOOKCAR_YN = ? WHERE BOOKCAR_NO = ?";
			  
			  try {
				  	ps=con.prepareStatement(sql);
				  	ps.setString(1, bookcar_yn);
				  	ps.setInt(2, bookcar_no);
				  	System.out.println("BookcarDao.java bookcarUpdate sql ::: " + sql);
				  	ps.executeUpdate();
					
				  
			} catch (Exception e) {
				System.out.println("bookcarUpdate의 전체출력"+e);
			}
			  
			 return ;
		  }		  
		  
		  
		
}
