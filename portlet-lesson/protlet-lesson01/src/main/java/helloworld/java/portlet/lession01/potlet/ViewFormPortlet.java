package helloworld.java.portlet.lession01.potlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewFormPortlet extends GenericPortlet {
	private String jsp_view = "/WEB-INF/views/viewform/view.jsp";
	
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
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
		String name = request.getParameter("name");
		
		System.out.println(">>>>> 提交了数据：name为" + name);
		
        response.setRenderParameter("msg", "> " + name +" <");
	}

}
