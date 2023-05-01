package pangpang.controller.member;

import java.io.IOException;
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
import pangpang.model.Dto.member.PageDto;
import pangpang.model.Dto.member.SaltDto;

/**
 * Servlet implementation class Info
 */
@WebServlet("/member/info")
public class Info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		String json = "";
		ObjectMapper objectMapper = new ObjectMapper();
 		
		if( type==1 ) {// 회원 리스트 뽑기 
			// -------------- 검색 처리 -----------------//
			// 1. 정렬
			String list = request.getParameter("list");			
			int order = Integer.parseInt(request.getParameter("order"));
			int rank = Integer.parseInt(request.getParameter("rank"));
			// ------------- page 처리 --------------- //
			// 1. 현재페이지[요청] , 2.페이지당 표시할게시물수 3.현재페이지[ 게시물시작  ]
			int page = Integer.parseInt( request.getParameter("page") );
			int listsize = Integer.parseInt( request.getParameter("listsize") ) ; // 화면에 표시할 게시물수 요청
			int startrow = (page-1)*listsize; // 해당 페이지에서의 게시물 시작번호 = 검색된 결과의 레코드중 인덱스번호
			// 프린트 할 총 개수
			int totalsize = MemberDao.getInstance().gettotalsize(rank);
			// 페이지 개수
			int totalpage = totalsize % listsize == 0 ? 	// 만약에 나머지가 0 이면 
							totalsize/listsize :  totalsize/listsize+1;
			int btnsize = 5; // 최대 페이징버튼 출력수
			int startbtn = ( (page-1) / btnsize ) * btnsize +1 ; 
			int endbtn = startbtn + (btnsize-1);
			// * 단 마지막버튼수가 총페이지수보다 커지면 마지막버튼수 총페이지수로 대입 
			if( endbtn > totalpage ) endbtn = totalpage;

			ArrayList<MemberDto> result = MemberDao.getInstance().getMemberList(startrow,listsize,list,order,rank);
			
			PageDto dto = new PageDto(page, listsize, startrow, totalsize, totalpage, btnsize, startbtn, endbtn, result);
			
			json = objectMapper.writeValueAsString(dto);
		}else if( type==2 ){ // 회원정보 상세보기
			// 1. 세션[Object]에 담겨진 회원아이디 호출 
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			// 2. 로그인한 회원의 정보 호출 [ 비밀번호 빼고 ] 
			MemberDto result =  MemberDao.getInstance().getMember(member_no);
			json = objectMapper.writeValueAsString(result);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// 회원가입
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String member_name = request.getParameter("member_name");
		String member_birth = request.getParameter("member_birth");
		String member_email = request.getParameter("member_email");
		String member_phone = request.getParameter("member_phone");
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		String member_address = request.getParameter("member_address");
		
		// 저장된 솔트 꺼내기
		ArrayList<SaltDto> salts = GetSalt.getSalt();
		
		// 최신 솔트 이용하여 암호화 하기
		String salt = salts.get(salts.size()-1).getSalt();
		System.out.println(salt);
		String sha = madesha.sha(member_pwd, salt);
		System.out.println(sha);
		
		MemberDto dto = new MemberDto(0, member_name, member_id, sha, member_email, member_phone, member_address, member_birth, 0);
				
		System.out.println(dto);
		
		boolean result = MemberDao.getInstance().signup(dto);
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		boolean result = false;
		
		if( type == 1 ) { // 회원정보 수정
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			String member_name = request.getParameter("member_name");
			String member_birth = request.getParameter("member_birth");
			String member_email = request.getParameter("member_email");
			String member_phone = request.getParameter("member_phone");
			String member_id = request.getParameter("member_id");
			String member_address = request.getParameter("member_address");
			int member_rank = Integer.parseInt(request.getParameter("member_rank"));
			 
			MemberDto dto = new MemberDto(member_no, member_name, member_id, null, member_email, member_phone, member_address, member_birth, member_rank);
			System.out.println(dto);
			result = MemberDao.getInstance().update(dto);
		}else if( type == 2 ){// 비밀번호 변경
			String member_npwd = request.getParameter("member_npwd");
			String member_pwd = request.getParameter("member_pwd");
			String member_id = (String)request.getSession().getAttribute("login");
			
			// 저장된 솔트 꺼내기
			ArrayList<SaltDto> salts = GetSalt.getSalt();
			
			// 마지막로그인 날짜 가져오기
			String logindate = MemberDao.getInstance().logindate(member_id);
			//System.out.println("logindate : "+logindate);
			
			// 솔트리스트에서 해당하는 솔트 찾기
			int cnt = 0;
			for(int i = 0 ; i<salts.size();i++) {
				System.out.println("날짜비교 : " + salts.get(i).getSdate().compareTo(logindate));
				if(salts.get(i).getSdate().compareTo(logindate)<0) {
					cnt=i;
				}else if(salts.get(i).getSdate().compareTo(logindate)>0){
					cnt=i-1;
					break;
				}else {
					cnt=i;
					break;
				}
			}
			//System.out.println("cnt :"+cnt);
			String salt = salts.get(cnt).getSalt();
			//System.out.println(salt);
			String sha = madesha.sha(member_pwd, salt);
			//System.out.println("sha : "+sha);
			
			//System.out.println(salts.get(salts.size()-1).getSalt());
			String salt2 = salts.get(salts.size()-1).getSalt();
			String sha2 = madesha.sha(member_npwd, salt2);
			//System.out.println(sha2);
			//System.out.println(member_npwd+"/"+member_pwd+"/"+member_id);
			result = MemberDao.getInstance().updatepwd(sha2,member_id,sha);
			
			if(result) {
				int member_no = MemberDao.getInstance().getMno(member_id);
				ArrayList<AccountDto> list = MemberDao.getInstance().getAccount(member_no);
				System.out.println(list);
				if(list!=null) { // 회원이 등록한 계좌가 있으면 실행
					String key1 = sha.substring(0,32);
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
					
					result = MemberDao.getInstance().setAcccount(list);
				}	
			}
		}
		
		
		
		response.getWriter().print(result);	
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		boolean result = false;
		
		if( type==1 ) { //본인이 탈퇴
			String member_id = (String)request.getSession().getAttribute("login");
			String member_pwd = request.getParameter("member_pwd");
			
			// 저장된 솔트 꺼내기
			ArrayList<SaltDto> salts = GetSalt.getSalt();
			
			// 마지막로그인 날짜 가져오기
			String logindate = MemberDao.getInstance().logindate(member_id);
			//System.out.println("logindate : "+logindate);
			
			// 솔트리스트에서 해당하는 솔트 찾기
			int cnt = 0;
			for(int i = 0 ; i<salts.size();i++) {
				System.out.println("날짜비교 : " + salts.get(i).getSdate().compareTo(logindate));
				if(salts.get(i).getSdate().compareTo(logindate)<0) {
					cnt=i;
				}else if(salts.get(i).getSdate().compareTo(logindate)>0){
					cnt=i-1;
					break;
				}else {
					cnt=i;
					break;
				}
			}
			System.out.println("cnt :"+cnt);
			String salt = salts.get(cnt).getSalt();
			System.out.println(salt);
			String sha = madesha.sha(member_pwd, salt);
			System.out.println("sha : "+sha);
			
			result = MemberDao.getInstance().delete(member_id,sha);
		}else if( type==2 ) {//관리자가 삭제
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			
			result = MemberDao.getInstance().dropMember(member_no);
		}
		response.getWriter().print(result);	
	}

}
