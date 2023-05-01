package pangpang.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pangpang.model.Dao.product.ProductDao;
import pangpang.model.Dao.product.StockDao;
import pangpang.model.Dto.product.PageDto;
import pangpang.model.Dto.product.ProductDto;

@WebServlet("/product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Product() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		if (type == 3) {// 제품 1개 가져오기
			int pno = Integer.parseInt(request.getParameter("pno"));
			ProductDto dto = ProductDao.getInstance().getProduct(pno);
			json = mapper.writeValueAsString(dto);
		}else {// 전체 제품 목록 가져오기// 카테고리별 제품 목록 가져오기// 검색 제품 목록 가져오기
			int cno = Integer.parseInt(request.getParameter("cno"));
			// --- 검색 처리 --- //		
			String key     = request.getParameter("key");
			String keyword = request.getParameter("keyword");						System.out.println(key+keyword);
			// --- page 처리 --- //
			int page     = Integer.parseInt(request.getParameter("page"));			System.out.println(page);
			int listsize = Integer.parseInt(request.getParameter("listsize")) ;		System.out.println(listsize);
			int startrow = (page-1)*listsize; 		
			// --- page 버튼 만들기 --- //
			// 1. 전체페이지수 , 2.페이지당 표시할 개수 3. 시작버튼 번호 
			int totalsize = ProductDao.getInstance().totalsizeP(cno, key, keyword);
			int totalpage = totalsize % listsize == 0 ? totalsize/listsize : totalsize/listsize+1 ;		
			// 최대 페이지버튼 출력수 
			int btnsize   = 3 ; 								
			int startbtn  = ((page-1)/btnsize)* btnsize+1;	    
			int endbtn    = startbtn + (btnsize - 1);					
			// * 단 마지막 페이지버튼수가 총 페이지 수보다 커지면 X     
			if(endbtn > totalpage) endbtn = totalpage ;
			
			
			ArrayList<ProductDto> list = ProductDao.getInstance().getProductList(type, cno, key, keyword, startrow, listsize);
			PageDto dto = new PageDto(list, page, listsize, startrow, totalsize, totalpage, btnsize, startbtn, endbtn);
			json = mapper.writeValueAsString(dto);
		}
		
		System.out.println(json);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

 		String uploadpath = request.getSession().getServletContext().getRealPath("/product/pimg");
 		
 		MultipartRequest multi = new MultipartRequest(
 				request,						// 요청방식
 				uploadpath,						// 첨부파일 가져와서 저장할 서버내 폴더
 				(1024*1024*10),					// 첨부파일 허용 범위 용량 //10MB
 				"UTF-8",						// 첨부파일 한글 인코딩
 				new DefaultFileRenamePolicy()	// 동일한 첨부파일명이 존재하면 뒤에 숫자 붙여서 식별
 				);
 		
 		int    category_no			= Integer.parseInt(multi.getParameter("category_no"));		System.out.println(category_no);
 		String product_name 		= multi.getParameter("product_name");						System.out.println(product_name);
		String product_option 		= multi.getParameter("product_option");						System.out.println(product_option);
		String product_unit 	    = multi.getParameter("product_unit");						System.out.println(product_unit);
		String product_content 	    = multi.getParameter("product_content");					System.out.println(product_content);
		String product_img 	        = multi.getFilesystemName("product_img");					System.out.println(product_img);
		int    product_price		= Integer.parseInt(multi.getParameter("product_price"));	System.out.println(product_price);
		int    product_discount		= Integer.parseInt(multi.getParameter("product_discount"));	System.out.println(product_discount);


		ProductDto dto = new ProductDto(0,product_name, product_option, product_unit, product_img, product_content,product_price,product_discount, category_no);
		boolean result = ProductDao.getInstance().item_register(dto); System.out.println(dto);
	

		response.getWriter().print(result);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

 		String uploadpath = request.getSession().getServletContext().getRealPath("/product/pimg");
 		
 		MultipartRequest multi = new MultipartRequest(
 				request,						// 요청방식
 				uploadpath,						// 첨부파일 가져와서 저장할 서버내 폴더
 				(1024*1024*10),					// 첨부파일 허용 범위 용량 //10MB
 				"UTF-8",						// 첨부파일 한글 인코딩
 				new DefaultFileRenamePolicy()	// 동일한 첨부파일명이 존재하면 뒤에 숫자 붙여서 식별
 				);

 		int    category_no			= Integer.parseInt(multi.getParameter("category_no"));	System.out.println(category_no);
 		String product_name 		= multi.getParameter("product_name_U");						System.out.println(product_name);
		String product_option 		= multi.getParameter("product_option_U");					System.out.println(product_option);
		String product_unit 	    = multi.getParameter("product_unit_U");						System.out.println(product_unit);
		String product_content 	    = multi.getParameter("product_content_U");					System.out.println(product_content);
		String product_img 	        = multi.getFilesystemName("product_img_U");					System.out.println(product_img);
		int    product_price		= Integer.parseInt(multi.getParameter("product_price_U"));	System.out.println(product_price);
		int    product_discount		= Integer.parseInt(multi.getParameter("product_discount_U"));System.out.println(product_discount);
		
		int    product_no			= Integer.parseInt(multi.getParameter("product_no"));		System.out.println(product_no);

		ProductDto dto = new ProductDto(product_no, product_name, product_option, product_unit, product_img, product_content,product_price,product_discount, category_no);
		
		boolean result = ProductDao.getInstance().item_update(dto);

		response.getWriter().print(result);
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int    pno			= Integer.parseInt(request.getParameter("pno"));
		boolean result = ProductDao.getInstance().item_delete(pno);
		response.getWriter().print(result);
		
	}

}
