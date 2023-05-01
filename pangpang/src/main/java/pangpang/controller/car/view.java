package pangpang.controller.car;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.car.viewDao;
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.car.BookcarDto;

/**
 * Servlet implementation class view
 */
@WebServlet("/view")
public class view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public view() {
        super();
        // TODO Auto-generated constructor stub
    }

    //상태전체출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid=(String)request.getSession().getAttribute("login");	
			System.out.println(mid);
		int member_no=MemberDao.getInstance().getMno(mid);
			System.out.println(member_no);
		ArrayList<BookcarDto>result = viewDao.getInstance().recordList(member_no);
		System.out.println("view.java get 과거상태출력확인"+result);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonArry= mapper.writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArry);			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
