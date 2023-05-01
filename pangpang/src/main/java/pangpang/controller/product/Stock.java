package pangpang.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.product.OrderDao;
import pangpang.model.Dao.product.StockDao;
import pangpang.model.Dto.product.PageDto;
import pangpang.model.Dto.product.StockDto;


@WebServlet("/stock")
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Stock() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int type = Integer.parseInt(request.getParameter("type")); 				System.out.println(type);
		// --- 검색 처리 --- //		
		String key     = request.getParameter("key");
		String keyword = request.getParameter("keyword");						System.out.println(key+keyword);
		// --- page 처리 --- //
		int page     = Integer.parseInt(request.getParameter("page"));			System.out.println(page);
		int listsize = Integer.parseInt(request.getParameter("listsize")) ;		System.out.println(listsize);
		int startrow = (page-1)*listsize; 		
		// --- page 버튼 만들기 --- //
		// 1. 전체페이지수 , 2.페이지당 표시할 개수 3. 시작버튼 번호 
		int totalsize = StockDao.getInstance().totalsize_stock(key, keyword);
		int totalpage = totalsize % listsize == 0 ? totalsize/listsize : totalsize/listsize+1 ;		
		// 최대 페이지버튼 출력수 
		int btnsize   = 5 ; 								
		int startbtn  = ((page-1)/btnsize)* btnsize+1;	    
		int endbtn    = startbtn + (btnsize - 1);					
		// * 단 마지막 페이지버튼수가 총 페이지 수보다 커지면 X     
		if(endbtn > totalpage) endbtn = totalpage ;
		
		ArrayList<StockDto> list = StockDao.getInstance().getStockList(type,key,keyword,startrow,listsize);
		PageDto dto = new PageDto(page, listsize, startrow, totalsize, totalpage, list, btnsize, startbtn, endbtn);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonarray = mapper.writeValueAsString(dto);
		
		response.setCharacterEncoding("UTF-8");				
		response.setContentType("appication/json");
		response.getWriter().print(jsonarray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int 	stockmanagementtype  	= Integer.parseInt(request.getParameter("stockmanagementtype"));
		String 	stockmanagementcompany	= request.getParameter("stockmanagementcompany");
		int 	stockmanagementamount	= Integer.parseInt(request.getParameter("stockmanagementamount"));
		String 	stockmanagementdate		= request.getParameter("stockmanagementdate");
		String 	stockmanagementenddate	= request.getParameter("stockmanagementenddate");
		int 	product_price			= Integer.parseInt(request.getParameter("product_price"));
		int 	product_no				= Integer.parseInt(request.getParameter("product_no"));
		
		StockDto dto = new StockDto(stockmanagementdate, stockmanagementenddate, stockmanagementtype, 
				stockmanagementcompany, stockmanagementamount, product_price, product_no);
		
		boolean result = StockDao.getInstance().setStock(dto);
		response.getWriter().print(result);
				
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int 	stockmanagementno  	= Integer.parseInt(request.getParameter("stockmanagementno"));
		boolean result = StockDao.getInstance().deleteStock(stockmanagementno);
		response.getWriter().print(result);		
	}

}
