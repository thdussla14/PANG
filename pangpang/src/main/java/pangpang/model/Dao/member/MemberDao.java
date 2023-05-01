package pangpang.model.Dao.member;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import pangpang.model.Dao.Dao;
import pangpang.model.Dto.member.AccountDto;
import pangpang.model.Dto.member.MemberDto;

public class MemberDao extends Dao{
	private static MemberDao dao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { return dao; }
	
	// 1. 회원가입
		public boolean signup( MemberDto dto ) {
			String sql = "insert into member(member_name, member_birth, member_email, member_phone, member_id, member_pwd, member_address) values(?,?,?,?,?,?,?);";
			try {
				ps = con.prepareStatement(sql);
				ps.setString( 1 , dto.getMember_name() );
				ps.setString( 2 , dto.getMember_birth() );
				ps.setString( 3 , dto.getMember_email() );
				ps.setString( 4 , dto.getMember_phone() );
				ps.setString( 5 , dto.getMember_id() );
				ps.setString( 6 , dto.getMember_pwd() );
				ps.setString( 7 , dto.getMember_address());
				int cnt = ps.executeUpdate(); 
				if(cnt == 1) {return true;}
			}catch (Exception e) {System.out.println(e);}
			return false;
		}
		 
	// 2. 이메일 중복검사
	public boolean getEmail( String member_email ) {
		String sql = "select * from member where member_email='"+member_email+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 3. 아이디 중복검사
	public boolean getId( String member_id ) {
		String sql = "select * from member where member_id='"+member_id+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 4. 로그인
	public int login( String member_id , String member_pwd ) {
		String sql = "select member_rank from member where member_id='"+member_id+"' and member_pwd='"+member_pwd+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("rs실행");
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	
	
	 
	// 5. 로그인한 회원 정보찾기
	public MemberDto getMember( int member_no ) {
		String sql = "select member_no, member_name, member_id, member_email, member_phone, member_address, member_birth, member_rank "
				+ "from member m where member_no = ?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, member_no);
			rs = ps.executeQuery();
			if( rs.next() ) {
				MemberDto dto = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				return dto;
			}
		}catch (Exception e) {System.out.println(e);} 
		return null;
	}
	
	
	// 6-1. 회원수 뽑기
	public int gettotalsize(int rank) {
		String sql = "select count(*) from member where member_rank>"+rank+";";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}return 0;
	}
	
	// 6-2. 회원 리스트 뽑기
	public ArrayList<MemberDto> getMemberList(int startrow,int listsize,String listname,int order,int rank) {
		ArrayList<MemberDto> list = new ArrayList<>();
		String sql = "";
		String order2 = "";
		if(order==2) {
			order2 = "asc";
		}else if(order==3) {
			order2 = "desc";
		}
		if( listname.equals("") && order<=1 ) { // 정렬이 없다.
			sql = " select member_no , member_name, member_id,  member_email, member_phone, "
					+ "member_address ,member_birth,member_rank "
					+ "from member where member_rank>"+rank+" limit ? , ?;";
		}else { // 검색이 있다.
			sql = "select member_no , member_name, member_id,  member_email, member_phone, "
					+ "member_address ,member_birth,member_rank "
					+ "from member where member_rank>"+rank+" order by "+listname+" "+order2+" limit ? , ?;";
		}
		try {
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , startrow ); 
			ps.setInt( 2 , listsize);
			rs = ps.executeQuery();
			while( rs.next() ) {
				MemberDto dto = new  MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getInt(8));
				list.add(dto);
			}return list;
		}catch (Exception e) {System.out.println(e);} 
		return list;
	}
	
	// 7. 회원id --> 회원mno 반환
	public int getMno( String member_id ) {
		String sql = "select member_no from member where member_id = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 , member_id );
			rs = ps.executeQuery();
			if( rs.next()  ) return rs.getInt(1);
		}catch (Exception e) {System.out.println(e);} 
		return 0;
	}
	
	// 8. 회원정보 수정
	public boolean update( MemberDto dto) {
		String sql = "update member set member_no=?,member_name=?,member_birth=?,member_email=?,member_phone=?,member_id=?,member_address=?,member_rank=? where member_no=? ;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getMember_no());
			ps.setString( 2 , dto.getMember_name() );
			ps.setString( 3 , dto.getMember_birth() );
			ps.setString( 4 , dto.getMember_email() );
			ps.setString( 5 , dto.getMember_phone() );
			ps.setString( 6 , dto.getMember_id() );
			ps.setString( 7 , dto.getMember_address());
			ps.setInt(8, dto.getMember_rank());
			ps.setInt(9, dto.getMember_no());
			int cnt = ps.executeUpdate();
			if(cnt==1) {return true;}
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 9. 아이디 찾기
	public String findid( String member_name , String member_email ) {
		String sql = "select member_id from member where member_name='"+member_name+"' and member_email='"+member_email+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
		
	// 10. 비밀번호 재발급
	public String findpwd( String member_name , String member_email , String member_id , String updatePwd ) {
		String sql = "select member_no from member where member_name = '"+member_name+"' and member_email = '"+member_email+"' and member_id = '"+member_id+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {  // 만약에 동일한 아이디와 이메일 일치한 레코드가 있으면 [ 찾았다. ]
				Date date = new Date();
				final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				System.out.println(simpleDateFormat.format(date));
				
				sql = "update member set member_pwd = ?,member_login='"+simpleDateFormat.format(date)+"' where member_no = ?";
				ps = con.prepareStatement(sql);
				ps.setString( 1 , updatePwd );		// 임시비밀번호로 업데이트
				ps.setInt( 2 ,  rs.getInt( 1 ) );	// select 에서 찾은 레코드의 회원번호 
				int result = ps.executeUpdate();	// 업데이트한 레코드 개수 반환
				if( result == 1 ) { // 업데이트한 레코드가 1개 이면 
					// -- 이메일전송 테스트 되는경우 만 -- //
					//new MemberDto().sendEmail( memail, updatePwd ); // 임시비밀번호를 이메일로 보내기 
					//return "true";
					// -- 이메일전송 테스트 안되는 경우 -- //
					return updatePwd;
				}
			}
		}catch (Exception e) {System.out.println(e);} 
		return "false";
	}
	// 11. 회원탈퇴
	public boolean delete( String member_id , String member_pwd ) {
		String sql = "delete from member where member_id = ? and member_pwd = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 , member_id );	ps.setString( 2 , member_pwd );
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; } 
		}catch (Exception e) {System.out.println(e);} return false;
	}
	
	
	// 12. 관리자가 회원 탈퇴 시키기
	public boolean dropMember( int member_no ) {
		String sql = "delete from member where member_no = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , member_no );
			int count = ps.executeUpdate();
			if( count == 1 ) { return true; } 
		}catch (Exception e) {System.out.println(e);} return false;
	}
	
	// 13. 비밀번호 변경
	public boolean updatepwd(String member_npwd,String member_id,String member_pwd) {
		Date date = new Date();
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(date));
		
		String sql = "update member set member_pwd=?,member_login='"+simpleDateFormat.format(date)+"' where member_id=? and member_pwd=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 , member_npwd );
			ps.setString( 2 , member_id );
			ps.setString( 3 , member_pwd );
			int cnt = ps.executeUpdate();
			if(cnt==1) {return true;}
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 14. 마지막 로그인 날짜
	public String logindate( String member_id ) {
		String sql = "select member_login from member where member_id='"+member_id+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 15. 비밀번호 가져오기
	public String getpwd(int member_no) {
		String sql = "select member_pwd from member where member_no='"+member_no+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 16. 계좌 등록하기
	public boolean addAccount(String account_bank,String account_number, int member_no) {
		String sql = "insert into account(account_bank,account_number,member_no) values(?,?,?);";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, account_bank);
			ps.setString(2, account_number);
			ps.setInt(3, member_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	}
	
	// 17. 계좌 가져오기 
	public ArrayList<AccountDto> getAccount(int member_no) {
		ArrayList<AccountDto> list = new ArrayList<>();
		String sql = "select * from account where member_no='"+member_no+"';";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				AccountDto dto = new AccountDto(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4));
				list.add(dto);
			}return list;
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 18. 계좌 수정
	public boolean setAcccount(ArrayList<AccountDto> list) {
		String sql = "update account set account_number = ? where account_no = ?;";
		try {
			for(AccountDto a : list) {
				ps = con.prepareStatement(sql);
				ps.setString(1, a.getAccount_number());
				ps.setInt(2, a.getAccount_no());
				ps.executeUpdate();
			}return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	}
	
	// 19. 계좌 삭제
	public boolean deleteAccount(int account_no) {
		String sql = "delete from account where account_no="+account_no+";";
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	}
}
