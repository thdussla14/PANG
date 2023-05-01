package pangpang.model.Dao.map;

import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.map.ChartDto;

public class ChartDao extends Dao{
	
	
	//싱글톤
	private static ChartDao dao= new ChartDao();
	private ChartDao() {}
	public static ChartDao getInstance() {
		return dao;
	}
	

	public ArrayList<ChartDto> getChart(){
		
		ArrayList<ChartDto> list = new ArrayList<>();
		
		String sql = "select DATE_FORMAT(ordermanagement_date, '%Y-%m-%d') , count(*) "
				+ " from ordermanagement "
				+ " group by DATE_FORMAT(ordermanagement_date, '%Y-%m-%d')"
				+ " order by DATE_FORMAT(ordermanagement_date, '%Y-%m-%d') desc limit 7 ";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				list.add( new ChartDto( rs.getString(1) , rs.getInt(2) ) );
			}
		} catch (Exception e) {
			System.out.println( e );
		}
		
		return list;
		
	}
	
}
