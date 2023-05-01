package pangpang.controller.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.controller.member.암호화.AES256;
import pangpang.controller.member.암호화.GetSalt;
import pangpang.controller.member.암호화.madesha;
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.member.AccountDto;
import pangpang.model.Dto.member.MemberDto;
import pangpang.model.Dto.member.SaltDto;


/**
 * Servlet implementation class Login
 */
@WebServlet("/member/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // 로그인한 회원정보 가져오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 세션[Object]에 담겨진 회원아이디 호출 
		int member_no = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));
		// 2. 로그인한 회원의 정보 호출 [ 비밀번호 빼고 ] 
		MemberDto result =  MemberDao.getInstance().getMember(member_no);
			
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print( json );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//로그인
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. AJAX에게 데이터 요청
		String member_pwd = request.getParameter("member_pwd");
		String member_id = request.getParameter("member_id");
		// 저장된 솔트 꺼내기
		ArrayList<SaltDto> salts = GetSalt.getSalt();
		// 마지막로그인 날짜 가져오기
		String logindate = MemberDao.getInstance().logindate(member_id);
		// 솔트리스트에서 해당하는 솔트 찾기
		int cnt = 0;
		for(int i = 0 ; i<salts.size();i++) {
			System.out.println("날짜비교 : " + salts.get(i).getSdate().compareTo(logindate));
			if(salts.get(i).getSdate().compareTo(logindate)<0) {
				cnt=i;
			}else if(salts.get(i).getSdate().compareTo(logindate)>0){
				cnt=i-1;
				break;
			}else {cnt=i;break;}
		}
		// 마지막 로그인 날짜의 솔트를 찾아 입력한 비밀번호를 해시처리
		String salt = salts.get(cnt).getSalt();
		String sha = madesha.sha(member_pwd, salt);
		
		// 2. DAO 호출해서 요청데이터를 보내서 결과 얻기 
		int result = MemberDao.getInstance().login( member_id , sha );
		//System.out.println("result : "+result);
		if( result != 0 ) {// 로그인에 성공하면 : 랭크를 들고온다.
			// 세션에 정보 저장
			request.getSession().setAttribute( "login", member_id );
			request.getSession().setAttribute( "rank", result );
			// 최신솔트 가져오기
			String salt2 = salts.get(salts.size()-1).getSalt();
			if(!salt.equals(salt2)) {// 로그인에 사용한 솔트가 최신이 아니라면 실행
				// 최신솔트로 다시 암호화해 DB저장
				String sha2 = madesha.sha(member_pwd, salt2);
				boolean result2 = MemberDao.getInstance().updatepwd(sha2, member_id, sha);
				if(result2) { // 비밀번호 업데이트에 성공하면 실행
					// 저장된 계좌번호 꺼내오기
					int member_no = MemberDao.getInstance().getMno(member_id);
					ArrayList<AccountDto> list = MemberDao.getInstance().getAccount(member_no);
					if(list!=null) { // 회원이 등록한 계좌가 있으면 실행
						// 지난번에 사용한 키로 복호화 -> 새로운 키로 암호화
						String key1 = sha.substring(0,32);
						String key2 = sha2.substring(0,32);
						for(AccountDto a : list) {
							try {
								String accountd = AES256.decrypt(key1, a.getAccount_number());
								String accounte = AES256.encrypt(key2, accountd);
								a.setAccount_number(accounte);
							} catch (Exception e) {
								System.out.println(e);
							}
						}System.out.println(list);
						result2 = MemberDao.getInstance().setAcccount(list);
						if(!result2) {result=0;};
					};
				}else {result=0;}
			}
			// 3. Dao 받은 결과를 AJAX에게 전달 
			response.getWriter().print(result);
		}
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
		// TODO Auto-generated method stub
	}

}
