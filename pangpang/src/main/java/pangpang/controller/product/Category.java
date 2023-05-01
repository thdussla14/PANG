package pangpang.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pangpang.model.Dao.product.ProductDao;
import pangpang.model.Dto.product.CategoryDto;

@WebServlet("/category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Category() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<CategoryDto>list = ProductDao.getInstance().getCategory();
		ObjectMapper mapper = new ObjectMapper();
		String jsonarray = mapper.writeValueAsString(list);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appication/json");
		response.getWriter().print(jsonarray);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
