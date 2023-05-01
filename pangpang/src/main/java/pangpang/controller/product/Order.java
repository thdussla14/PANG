package pangpang.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.product.OrderDao;
import pangpang.model.Dto.product.CartDto;
import pangpang.model.Dto.product.OrderDto;
import pangpang.model.Dto.product.PageDto;



@WebServlet("/order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Order() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type")); 				System.out.println(type);
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		
		if(type == -2) { //주문상세 출력
			int ordermanagement_no = Integer.parseInt(request.getParameter("ordermanagement_no"));					
			OrderDto dto = OrderDao.getInstance().getOrderDetail(ordermanagement_no);
			json = mapper.writeValueAsString(dto);
		}else {
			int mno = Integer.parseInt(request.getParameter("mno"));				System.out.println(mno);
			// --- 검색 처리 --- //		
			String key     = request.getParameter("key");
			String keyword = request.getParameter("keyword");						System.out.println(key+keyword);
			// --- page 처리 --- //
			int page     = Integer.parseInt(request.getParameter("page"));			System.out.println(page);
			int listsize = Integer.parseInt(request.getParameter("listsize")) ;		System.out.println(listsize);
			int startrow = (page-1)*listsize; 		
			// --- page 버튼 만들기 --- //
			// 1. 전체페이지수 , 2.페이지당 표시할 개수 3. 시작버튼 번호 
			int totalsize = OrderDao.getInstance().totalsize_order(key, keyword);
			int totalpage = totalsize % listsize == 0 ? totalsize/listsize : totalsize/listsize+1 ;		
			// 최대 페이지버튼 출력수 
			int btnsize   = 5 ; 								
			int startbtn  = ((page-1)/btnsize)* btnsize+1;	    
			int endbtn    = startbtn + (btnsize - 1);					
			// * 단 마지막 페이지버튼수가 총 페이지 수보다 커지면 X     
			if(endbtn > totalpage) endbtn = totalpage ;
			
			ArrayList<OrderDto> list = OrderDao.getInstance().getOrderList(type,mno,key,keyword,startrow,listsize);
			
			PageDto dto = new PageDto(page, listsize, startrow, totalsize, totalpage, btnsize, startbtn, endbtn, list);
			json = mapper.writeValueAsString(dto);
		}

		response.setCharacterEncoding("UTF-8");				
		response.setContentType("appication/json");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int 	member_no				= Integer.parseInt(request.getParameter("member_no"));		
		String 	ordermanagement_address = request.getParameter("buyer_addr");						
		String 	payment_how 			= request.getParameter("pg");							System.out.println(payment_how);	
		int 	payment_price 			= Integer.parseInt(request.getParameter("amount"));		System.out.println(payment_price);		
		
		String  json					= request.getParameter("orderlist").toString();				
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<CartDto> list = mapper.readValue(json, new TypeReference<ArrayList<CartDto>>(){});

		OrderDto dto = new OrderDto(ordermanagement_address, member_no, payment_how, payment_price, list);
		
		boolean result = OrderDao.getInstance().setOrder(dto);
		
		response.getWriter().print(result);
	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ordermanagement_no = Integer.parseInt(request.getParameter("ordermanagement_no"));
		int state			   = Integer.parseInt(request.getParameter("state"));
		
		boolean result = OrderDao.getInstance().updateState(state, ordermanagement_no);
		
		response.getWriter().print(result);		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
