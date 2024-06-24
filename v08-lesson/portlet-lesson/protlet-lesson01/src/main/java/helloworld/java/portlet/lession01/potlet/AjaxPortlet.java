package helloworld.java.portlet.lession01.potlet;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class AjaxPortlet extends GenericPortlet {
	private String jsp_view = "/WEB-INF/views/ajax/view.jsp";
	
	@Override
	public void init(PortletConfig config) throws PortletException {
		super.init(config);
		System.out.println(">>>>> 初始化Portlet");
	}
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		response.setContentType(request.getResponseContentType());
		
		System.out.println(">>>>> 访问视图页面");

		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(jsp_view);
		rd.include(request, response);
	}
	
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
		String name = request.getParameter("name");
		System.out.println(">>>>> 提交了数据：name为" + name);
		
		name = "{\"name\" : \"★ " + name + " ★\"}";
		System.out.print(" ; 响应数据为 ： " + name);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(name);
	}
}
