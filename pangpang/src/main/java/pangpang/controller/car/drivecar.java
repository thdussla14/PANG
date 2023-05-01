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

import pangpang.model.Dao.car.DrivecarDao;
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.car.DrivecarDto;

/**
 * Servlet implementation class drivecar
 */
@WebServlet("/drivecar")
public class drivecar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drivecar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * //현재 서버의 배포된 프로잭트내 폴도 경로 찾기 String uploadpath =
		 * request.getSession().getServletContext().getRealPath("/car/img");
		 * System.out.println("-------uploadpath-------");
		 * System.out.println(uploadpath);
		 * 
		 * //업로드 MultipartRequest multi = new MultipartRequest( request, //1.요청방식
		 * uploadpath, //2.첨부파일 가져와서 저장할 서버내 폴더 1024*1024*10 , //3.첨부파일 허용 범위 용량
		 * [바이트단위]//얘는 10메가임 "UTF-8", //4.첨부파일 한글 인코딩 new DefaultFileRenamePolicy()
		 * //5.동일한 첨부파일명이 존재하면 뒤에 숫자 붙여짐 그래서 판별함 ); String carmanage_img =
		 * multi.getFilesystemName("carmanage_img");
		 */
		int type = Integer.parseInt(request.getParameter("type"));
		
		if(type==1) {
			 String mid=(String)request.getSession().getAttribute("login");
			  System.out.println("drivecar 서블릿 mid확인::::"+mid); 
			  int member_no =MemberDao.getInstance().getMno(mid); 
			 
			  ArrayList<DrivecarDto>result =
					  				DrivecarDao.getInstance().drivereportAll(member_no);
			  System.out.println("bookcar result확인:::"+result);
			  
			  ObjectMapper mapper = new ObjectMapper();
			  String jsonArry= mapper.writeValueAsString(result);
			  response.setCharacterEncoding("UTF-8");
			  response.setContentType("application/json");
			  response.getWriter().print(jsonArry);
		}else if(type==2) {
			ArrayList<DrivecarDto>result =
	  				DrivecarDao.getInstance().drivereportAll2();
					System.out.println("bookcar result확인:::"+result);
					
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String reportday=request.getParameter("reportday"); //작성일자
				System.out.println(reportday);	
			String drivecar_distance=request.getParameter("drivecar_distance");
				System.out.println(drivecar_distance); //행선지
			String purpose=request.getParameter("purpose"); 
				System.out.println(purpose);	//운행목적
			String report_content= request.getParameter("report_content");
				System.out.println(report_content); //보고서내용
			int bookcar_no=Integer.parseInt(request.getParameter("bookcar_no"));
				System.out.println(bookcar_no); //배차넘버
			
			boolean result =DrivecarDao.getInstance().drivereport(reportday,drivecar_distance,purpose, report_content,bookcar_no); 
				response.getWriter().print(result);
				
	}

}
