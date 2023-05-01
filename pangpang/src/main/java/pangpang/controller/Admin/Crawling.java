package pangpang.controller.Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dto.Admin.WeatherDto;


@WebServlet("/crawling")
public class Crawling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Crawling() { super(); }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String URL = "https://weather.naver.com/today/02271102?cpName=KMA";
		
		try {
			Document doc = Jsoup.connect(URL).get();
			
			
			Elements element = doc.select("div.weather_area");
				System.out.println(element);
				System.out.println(element.text() );
				
			String current = element.select(".current").text();
				System.out.println( current );
			String summary = element.select(".summary").text();
				System.out.println( summary );
				
			
			WeatherDto dto = new WeatherDto(current, summary);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString( dto );
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(json) ;
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
