package pangpang.model.Dao.car;


import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.car.BookcarDto;

public class viewDao  extends Dao {
	
	//싱글톤
	private static viewDao dao= new viewDao();
	private viewDao() {}
	public static viewDao getInstance() {
		return dao;
	}		

	
	//나의 과거 배차상태들확인
	public ArrayList<BookcarDto>recordList(int member_no){
		ArrayList<BookcarDto>list = new ArrayList<>();
		String sql="select bookcar_no, bookcar_str_date, bookcar_end_date, bookcar_yn, carmanage_no, member_no, ifnull(reason, '') from bookcar where member_no=? ;";
		
		try {
	         ps=con.prepareStatement(sql);
	         ps.setInt(1, member_no);
	         rs=ps.executeQuery();			
	         
	         while(rs.next()) {
		         	BookcarDto dto = new BookcarDto(
		         			rs.getInt(1),
		         			rs.getString(2),
		         			rs.getString(3),
		         			rs.getString(4),
		         			rs.getInt(5),
		         			rs.getInt(6),
		         			rs.getString(7)
		         			);
		         	list.add(dto);
	         }
	         System.out.println("viewDao recordList담겼나확인해"+list);
		}catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	
}//class e
