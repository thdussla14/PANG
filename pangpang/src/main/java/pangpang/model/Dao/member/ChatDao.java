
package pangpang.model.Dao.member;

import java.sql.ResultSet;
import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.Admin.ChatDto;

public class ChatDao extends Dao{
	private static ChatDao dao = new ChatDao();
	private ChatDao() {}
	public static ChatDao getInstance() { return dao; }
	
	// 1. 채팅 보내기
	public synchronized boolean setChat ( ChatDto dto ) {
		String sql = "insert into chat( chat_msg , chat_fmno , chat_tmno )values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 ,  dto.getChat_msg() ); 
			ps.setInt( 2 ,  dto.getChat_fmno() );	
			ps.setInt( 3 ,  dto.getChat_tmno());
			ps.executeUpdate();
			return true;
		}catch (Exception e) { 	System.out.println(e); 	}  return false;
	}
	
	// 6. 채팅 출력
	public synchronized ArrayList<ChatDto> getChatList( int memno , int youmno ){
		ArrayList<ChatDto> list = new ArrayList<>();
		
		String sql = " select * from chat where "
					+ " ( ( chat_fmno = ? and chat_tmno = ? ) or  (chat_fmno = ? and chat_tmno = ? ) ) order by chat_no";
		
		try {
			ps = con.prepareStatement(sql);	
			ps.setInt( 1 , memno );		
			ps.setInt( 2 , youmno );
			ps.setInt( 3 , youmno );	
			ps.setInt( 4 , memno );
		
			rs = ps.executeQuery();
			while( rs.next() ) {
				ChatDto dto =  new ChatDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),rs.getString(5));
				// 보낸회원의 정보 호출 
				sql ="select member_name , member_id from member where member_no = " + rs.getInt(2);	// rs.getInt(2) = frommno
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				if( rs2.next() ) { 
					dto.setChat_fmname(rs2.getString(1));
					dto.setChat_fmid(rs2.getString(2));
				}
				list.add(  dto  );
			}
		}catch (Exception e) { 	System.out.println(e); 	}
		return list;
	}
	
}

