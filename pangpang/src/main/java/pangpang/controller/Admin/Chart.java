package pangpang.controller.Admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.map.ChartDao;
import pangpang.model.Dto.map.ChartDto;


@WebServlet("/chart")
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Chart() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ChartDto> result = ChartDao.getInstance().getChart();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString( result );
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
