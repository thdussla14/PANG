
package pangpang.controller.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.member.ChatDao;
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.Admin.ChatDto;

/**
 * Servlet implementation class MemberChat
 */
@WebServlet("/member/chat")
public class MemberChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberChat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memno = MemberDao.getInstance().getMno( (String)request.getSession().getAttribute("login") );
		int youmno = Integer.parseInt( request.getParameter("youmno") );
		
		//채팅 리스트 가져오기
		ArrayList<ChatDto> result = ChatDao.getInstance().getChatList(memno, youmno);
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();	String jsonArray = mapper.writeValueAsString(result);
		//
		response.setCharacterEncoding("UTF-8"); response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 1
		String chat_msg	= request.getParameter("chat_msg");
		int chat_fmno 	= MemberDao.getInstance().getMno(
							(String)request.getSession().getAttribute("login") );
		int chat_tmno 	= Integer.parseInt( request.getParameter("chat_tmno") );
		// 2
		ChatDto dto = new ChatDto(chat_msg, chat_fmno, chat_tmno);	System.out.println( "dto : " + dto );
		// 3
		boolean result = ChatDao.getInstance().setChat(dto);
		
		//4. 만약에 채팅 등록 성공했으면 tomno 에게 소켓 알림 메시지 보내기
		if( result ) {
			// 서버소켓에게 채팅을 받은 유저의 번호와 내용 을 전달 
			try {Chatting.onMessage( null ,  chat_tmno+","+chat_msg+","+chat_fmno);}
			catch (Exception e) { e.printStackTrace(); }
		}
		
		response.getWriter().print(result);
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
