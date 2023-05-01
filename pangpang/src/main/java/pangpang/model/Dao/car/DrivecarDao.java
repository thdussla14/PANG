package pangpang.model.Dao.car;


import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.car.DrivecarDto;


public class DrivecarDao extends Dao {
	//싱글톤
	private static DrivecarDao dao= new DrivecarDao();
	private DrivecarDao() {}
	public static DrivecarDao getInstance() {
		return dao;
	}	
	//운행일지등록
	public boolean drivereport(String reportday,String drivecar_distance,String purpose, String report_content, int bookcar_no) {
		String sql="insert into drivecar ( drivecar_str_date , drivecar_end_date , drivecar_distance , drivecar_parking , bookcar_no, report_content)\r\n"
				+ "values ( \r\n"
				+ "         (select bookcar_str_date from bookcar where bookcar_no = ? ) ,\r\n"
				+ "		 	(select bookcar_end_date from bookcar where bookcar_no = ? ) ,\r\n"
				+ "            ?,\r\n"
				+ "            ?,\r\n"
				+ "            ?,\r\n"
				+ "            ?\r\n"				
				+ "      );";
			
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, bookcar_no);
			ps.setInt(2, bookcar_no);
			ps.setString(3, drivecar_distance);
			ps.setString(4, purpose);
			ps.setInt(5, bookcar_no);
			ps.setString(6,report_content );
			ps.executeUpdate();
			
			return true;
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
	//운행일지 전체출력 당사자꺼만 [마이페이지]
	  public ArrayList<DrivecarDto> drivereportAll(int member_no) {
	  ArrayList<DrivecarDto>list = new ArrayList<>() ; String
	  sql="select d.*, c.carmanage_img, c.carmanage_name, c.carmanage_number,m.member_name\r\n"
	  		+ "  from drivecar d,carmanage c, bookcar b, member m\r\n"
	  		+ " where m.member_no = ?\r\n"
	  		+ "   and b.member_no = m.member_no\r\n"
	  		+ "   and b.carmanage_no = c.carmanage_no\r\n"
	  		+ "   and b.bookcar_no = d.bookcar_no; ;";
	  
	  try {
		  ps=con.prepareStatement(sql);
		  ps.setInt(1, member_no);
		  //ps.setString(2, carmanage_img);
		  rs=ps.executeQuery(); 
		  while(rs.next()) {
		  DrivecarDto dto = new DrivecarDto(
				  	rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
				  	rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
				  	rs.getString(9), rs.getString(10), rs.getString(11) ); list.add(dto); };
				  	System.out.println("drivereportAll list ::: " + list);
	  
	  }catch (Exception e) { System.out.println(e); 
	  		} return list; 
	  }
	  
	//운행일지 전체출력
	  public ArrayList<DrivecarDto> drivereportAll2() {
	  ArrayList<DrivecarDto>list = new ArrayList<>() ; String
	  sql="select d.*, c.carmanage_img, c.carmanage_name, c.carmanage_number,m.member_name\r\n"
	  		+ "  from drivecar d,carmanage c, bookcar b, member m\r\n"
	  		+ " where "
	  		+ "   b.member_no = m.member_no\r\n"
	  		+ "   and b.carmanage_no = c.carmanage_no\r\n"
	  		+ "   and b.bookcar_no = d.bookcar_no; ;";
	  
	  try {
		  ps=con.prepareStatement(sql);
		  //ps.setString(2, carmanage_img);
		  rs=ps.executeQuery(); 
		  while(rs.next()) {
		  DrivecarDto dto = new DrivecarDto(
				  	rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
				  	rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
				  	rs.getString(9), rs.getString(10), rs.getString(11) ); list.add(dto); };
				  	System.out.println("drivereportAll list ::: " + list);
	  
	  }catch (Exception e) { System.out.println(e); 
	  		} return list; 
	  }
	
	
	
	
};
