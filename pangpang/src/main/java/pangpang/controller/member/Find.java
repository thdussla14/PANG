package pangpang.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pangpang.controller.member.암호화.AES256;
import pangpang.controller.member.암호화.GetSalt;
import pangpang.controller.member.암호화.madesha;
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.member.AccountDto;
import pangpang.model.Dto.member.SaltDto;


/**
 * Servlet implementation class Find
 */
@WebServlet("/member/find")
public class Find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Find() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		String member_name = request.getParameter("member_name");
		String member_email = request.getParameter("member_email");
		
		String result = null;
		
		if( type == 1 ) {// 아이디 찾기
			result = MemberDao.getInstance().findid( member_name , member_email );
		}else if( type == 2 ) { // 임시 비밀번호 발급
			String member_id = request.getParameter("member_id");
			
			Random random = new Random();
			// 표현할 난수 문자 목록 
			String ranStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			// 임시비밀번호 만들기 
			String updatePwd = "";
			for( int i = 0 ; i<12 ; i++ ) { // 12자리수
				// ranStr 문자열에서 0인덱스~마지막인덱스 의 난수 인덱스 만들기
				int ran = random.nextInt( ranStr.length() );
				updatePwd += ranStr.charAt( ran );	// 난수로 생성된 인덱스의 문자1개 추출해서 대입
			} // for end 
			System.out.println("updatePwd"+updatePwd);
			
			// 저장된 솔트 꺼내기
			ArrayList<SaltDto> salts = GetSalt.getSalt();
			
			// 최신 솔트 이용하여 암호화 하기
			String salt = salts.get(salts.size()-1).getSalt();
			System.out.println(salt);
			String sha2 = madesha.sha(updatePwd, salt);
			
			String key1 = MemberDao.getInstance().getpwd(MemberDao.getInstance().getMno(member_id)).substring(0,32);
			
			result = MemberDao.getInstance().findpwd( member_name , member_email , member_id , sha2 );
			System.out.println(result);
			
			if(result.equals(sha2)) {
				result=updatePwd;
			
				int member_no = MemberDao.getInstance().getMno(member_id);
				ArrayList<AccountDto> list = MemberDao.getInstance().getAccount(member_no);
				System.out.println(list);
				if(list!=null) { // 회원이 등록한 계좌가 있으면 실행
					String key2 = sha2.substring(0,32);
					for(AccountDto a : list) {
						try {
							String accountd = AES256.decrypt(key1, a.getAccount_number());
							//System.out.println(account1);
							String accounte = AES256.encrypt(key2, accountd);
							//System.out.println(account2);
							a.setAccount_number(accounte);
						} catch (Exception e) {
							System.out.println(e);
						}
					}System.out.println(list);
					
					Boolean result2 = MemberDao.getInstance().setAcccount(list);
					if(!result2) {result="false";};
				}
			}
		}
		response.getWriter().print( result ); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
