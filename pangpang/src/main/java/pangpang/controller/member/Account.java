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
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.member.AccountDto;


@WebServlet("/member/account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int member_no = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));
		String key = MemberDao.getInstance().getpwd(member_no).substring(0,32);
		ArrayList<AccountDto> list = MemberDao.getInstance().getAccount(member_no);
		ArrayList<AccountDto> list2 = new ArrayList<>();
		for(AccountDto a : list) {
			try {
				AccountDto dto = new AccountDto(a.getAccount_no(),a.getAccount_bank(),AES256.decrypt(key, a.getAccount_number()),a.getMember_no());
				list2.add(dto);
			} catch (Exception e) {
				System.out.println(e);
			} 
		}
		System.out.println(list);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list2);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String account_bank = request.getParameter("account_bank");
		String account_number = request.getParameter("account_number");
		int member_no = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));
		String key = MemberDao.getInstance().getpwd(member_no).substring(0,32);
		try {
			String aes = AES256.encrypt(key, account_number);
			//System.out.println(aes);
			//System.out.println(AES256.decrypt(key, aes));
			boolean result = MemberDao.getInstance().addAccount(account_bank,aes,member_no);
			
			response.getWriter().print(result);
		} catch (Exception e) {
			System.out.println(e);;
		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int account_no = Integer.parseInt(request.getParameter("account_no"));	
		boolean result = MemberDao.getInstance().deleteAccount(account_no);
		response.getWriter().print(result);
		
	}

}
