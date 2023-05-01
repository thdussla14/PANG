package pangpang.controller.car;

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

import pangpang.model.Dao.car.BookcarDao;
import pangpang.model.Dao.car.CarmanagementDao;
import pangpang.model.Dto.car.BookcarDto;
import pangpang.model.Dto.car.CarmanagementDto;

/**
 * Servlet implementation class Carmanage
 */
@WebServlet("/carmanage")
public class Carmanage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carmanage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // 차량관리 출력구현
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type=request.getParameter("type");
	
		if(type.equals("1")) { //carmanagement 전체출력
			
			System.out.println("[GET] carmanage_no ::: " + request.getParameter("carmanage_no"));
			ArrayList<CarmanagementDto>result = null;
			
			if ( request.getParameter("carmanage_no") != null ) {
				
				int carmanage_no = Integer.parseInt( request.getParameter("carmanage_no") );
				System.out.println("carmanage_no 값 있따 !!! " + carmanage_no );
				result =CarmanagementDao.getInstance().getCarInfo( carmanage_no );
				System.out.println("carmanage_no 결과값 !!! " + result);
				
			} else {
				
				result =CarmanagementDao.getInstance().carList();
			}
			/* CarmanagementDto dto = new CarmanagementDto(); */
			ObjectMapper mapper = new ObjectMapper();
			String jsonArry= mapper.writeValueAsString(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArry);				
	
		}else if(type.equals("2")) { //bookcar전체출력

			String mid=(String)request.getSession().getAttribute("login");
			System.out.println("--------------");	
			System.out.println(mid);
			
			
			ArrayList<BookcarDto>result =BookcarDao.getInstance().bookcarlist();
			System.out.println("bookcar result확인:::"+result);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonArry= mapper.writeValueAsString(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArry);
			
		}else if(type.equals("3")){	//배차관리 선택을 위한 전체출력
			
			System.out.println("[GET] carmanage_no ::: " + request.getParameter("carmanage_no"));
			ArrayList<CarmanagementDto>result = null;		
			
			result =BookcarDao.getInstance().carListy();
			ObjectMapper mapper = new ObjectMapper();
			String jsonArry= mapper.writeValueAsString(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArry);				
	
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// 차량관리 등록구현 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//현재 서버의 배포된 프로잭트내 폴도 경로 찾기
		String uploadpath = request.getSession().getServletContext().getRealPath("/car/img");
		System.out.println("-------uploadpath-------");
		System.out.println(uploadpath);
		
		//업로드
		MultipartRequest multi = new MultipartRequest(
				request, 						//1.요청방식
				uploadpath, 					//2.첨부파일 가져와서 저장할 서버내 폴더
				1024*1024*10 ,					//3.첨부파일 허용 범위 용량 [바이트단위]//얘는 10메가임
				"UTF-8",						//4.첨부파일 한글 인코딩 
				new DefaultFileRenamePolicy()	//5.동일한 첨부파일명이 존재하면 뒤에 숫자 붙여짐 그래서 판별함
		);
		
		String carmanage_number = multi.getParameter("carmanage_number");	
		String carmanage_name = multi.getParameter("carmanage_name");
		String carmanage_img = multi.getFilesystemName("carmanage_img");
		String carmanage_use_yn =multi.getParameter("carmanage_use_yn");
		String carmanage_start =multi.getParameter("carmanage_start");	
		String carmanage_finish =multi.getParameter("carmanage_finish");	
		
		CarmanagementDto dto = new CarmanagementDto(carmanage_number, carmanage_name, carmanage_img, carmanage_use_yn, carmanage_start, carmanage_finish);
				
		System.out.println("CarmanagementDto dto:"+dto);
		boolean result = CarmanagementDao.getInstance().regi(dto);
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//현재 서버의 배포된 프로잭트내 폴도 경로 찾기
		String uploadpath = request.getSession().getServletContext().getRealPath("/car/img");
		System.out.println("-------uploadpath-------");
		System.out.println(uploadpath);
		
		System.out.println("doPut request updateFormData " + request.getParameter("updateFormData"));
		
		//업로드
		MultipartRequest multi = new MultipartRequest(
				request, 						//1.요청방식
				uploadpath, 					//2.첨부파일 가져와서 저장할 서버내 폴더
				1024*1024*10 ,					//3.첨부파일 허용 범위 용량 [바이트단위]//얘는 10메가임
				"UTF-8",						//4.첨부파일 한글 인코딩 
				new DefaultFileRenamePolicy()	//5.동일한 첨부파일명이 존재하면 뒤에 숫자 붙여짐 그래서 판별함
		);
		
		int carmanage_no = Integer.parseInt(multi.getParameter("carmanage_no"));
		String carmanage_img = multi.getParameter("carmanage_img");
			System.out.println("carmanage_img : " + carmanage_img );
		String carmanage_use_yn =multi.getParameter("carmanage_use_yn");
		String carmanage_finish =multi.getParameter("carmanage_finish");	
		
		CarmanagementDto dto = new CarmanagementDto(carmanage_no, carmanage_img, carmanage_use_yn, carmanage_finish);
				System.out.println("dto:::::::"+dto);
		boolean result =CarmanagementDao.getInstance().carupdate(dto);
		response.getWriter().print(result);
				
		/*
		 * System.out.println("CarmanagementDto dto:"+dto); boolean result =
		 * CarmanagementDao.getInstance().carupdate(dto);
		 * response.getWriter().print(result);
		 */
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int carmanage_no = Integer.parseInt(request.getParameter("carmanage_no")); 
	         System.out.println("-----------cno carmanage.java에서 확인-------------");
				 System.out.println(carmanage_no); 
	      boolean result = CarmanagementDao.getInstance().cardelete(carmanage_no);
	            System.out.println(result);
	      response.getWriter().print(result);
	}

}
