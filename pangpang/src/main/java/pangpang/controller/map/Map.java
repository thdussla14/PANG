package pangpang.controller.map;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.map.MapDao;
import pangpang.model.Dao.member.MemberDao;
import pangpang.model.Dto.map.MapOderDto;
import pangpang.model.Dto.map.MapcarDto;

@WebServlet("/map")
public class Map extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Map() { super(); }

	
    ObjectMapper mapper = new ObjectMapper();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = Integer.parseInt( request.getParameter("type") );
		
		String json = "";
		
		if( type == 1 ) {
		
			int member_no = MemberDao.getInstance().getMno( (String)request.getSession().getAttribute("login") );
			System.out.println( member_no );
			
			MapcarDto result = MapDao.getInstance().getBCarinfo( member_no );
			json = mapper.writeValueAsString( result );
			
			
		} else if( type == 2 ) {
			
			int order = Integer.parseInt( request.getParameter("order") );
			
			ArrayList<MapOderDto> result = MapDao.getInstance().getOrderList( order );
			json = mapper.writeValueAsString( result );
			
		} else if( type == 3 ) {
			
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print( json );
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
