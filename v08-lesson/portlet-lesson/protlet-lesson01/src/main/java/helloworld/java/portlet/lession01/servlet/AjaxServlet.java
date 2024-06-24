package helloworld.java.portlet.lession01.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(">>>>> 提交了数据：name为" + name);
		
		name = "{\"name\" : \"☆ " + name + " ☆\"}";
		System.out.print(" ; 响应数据为 ： " + name);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(name);
	}

}