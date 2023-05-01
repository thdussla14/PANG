package pangpang.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.car.BookcarDao;
import pangpang.model.Dao.car.CarmanagementDao;
import pangpang.model.Dao.member.MemberDao;

/**
 * Servlet implementation class bookcar
 */ 
@WebServlet("/bookcar")
public class bookcar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookcar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //입력받은 정보
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String mid=(String)request.getSession().getAttribute("login");
				System.out.println("mid:::"+mid);
			int mno=MemberDao.getInstance().getMno(mid);	
				System.out.println("mno:::"+mno);
			int	carmanage_no =Integer.parseInt(request.getParameter("carmanage_no"));
	
			String bookcar_destination=request.getParameter("bookcar_destination");
				System.out.println(bookcar_destination);
			String bookcar_str_date=request.getParameter("bookcar_str_date");
				System.out.println(bookcar_str_date);
			String bookcar_end_date=request.getParameter("bookcar_end_date");
				System.out.println(bookcar_end_date );
				
			boolean result=BookcarDao.getInstance().book(mno, carmanage_no ,bookcar_destination,bookcar_str_date,bookcar_end_date);
			
				System.out.println("bookcar 확인"+result);
				
				response.getWriter().print(result);
				/*
				 * ObjectMapper mapper = new ObjectMapper(); String jsonArry=
				 * mapper.writeValueAsString(result); response.setCharacterEncoding("UTF-8");
				 * response.setContentType("application/json");
				 * response.getWriter().print(jsonArry);
				 */
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookcar_yn = request.getParameter("bookCar_yn");
		System.out.println("bookcar.java doPost bookCar_yn ::: " + bookcar_yn);
		String reason=request.getParameter("reason");
		System.out.println("bookcar.java.doPost.reason"+reason);
		
		int bookcar_no = Integer.parseInt(request.getParameter("bookCar_no")) ;
		System.out.println("bookcar.java doPost bookCar_no ::: " + bookcar_no);
		if(reason==null) {
			BookcarDao.getInstance().bookcarUpdate2(bookcar_yn, bookcar_no);
			System.out.println("-- doPost 종료 --");
		}else {
			BookcarDao.getInstance().bookcarUpdate(bookcar_yn, bookcar_no,reason);
			System.out.println("-- doPost 종료 --");
		}

		
		
		
		
		
		/*
		 * String result = BookcarDao.getInstance().bookcarUpdate(bookcar_yn,
		 * bookcar_no); response.getWriter().print(result);
		 */
	}

}
