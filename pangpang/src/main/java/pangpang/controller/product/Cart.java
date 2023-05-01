package pangpang.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dao.product.OrderDao;
import pangpang.model.Dao.product.ProductDao;
import pangpang.model.Dto.product.CartDto;
import pangpang.model.Dto.product.CategoryDto;

@WebServlet("/cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Cart() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 장바구니 출력
		// 로그인한 회원 정보 
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));

		
		ArrayList<CartDto> list = OrderDao.getInstance().printCart(mno);
		ObjectMapper mapper = new ObjectMapper();
		String jsonarray = mapper.writeValueAsString(list);
		System.out.println("json"+jsonarray);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonarray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 장바구니 등록 [ 제품번호, 수량 ] 
		int amount 	= Integer.parseInt(request.getParameter("amount")) ; System.out.println(amount);
		int pno 	= Integer.parseInt(request.getParameter("pno")) ;	 System.out.println(pno);
		// 로그인한 회원 정보 
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));
		
		int result = OrderDao.getInstance().cartIn(amount, pno, mno);  System.out.println(result);
		response.getWriter().print(result);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int type 	= Integer.parseInt(request.getParameter("type"));
		
		// 로그인한 회원 정보 
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login")); System.out.println(mno);
		
		boolean result = false;
		
		if(type == 1) {       // 로그인한 회원 전체 제품 장바구니 취소
			result  = OrderDao.getInstance().cartOutAll(mno);
			response.getWriter().print(result);
			
		}else if(type == 2) { // 로그인한 회원 선택 제품 장바구니 취소 
			int pno = Integer.parseInt(request.getParameter("pno")); System.out.println(pno);
			result  = OrderDao.getInstance().cartOut(pno, mno);
			response.getWriter().print(result);
		}		

	}

}
