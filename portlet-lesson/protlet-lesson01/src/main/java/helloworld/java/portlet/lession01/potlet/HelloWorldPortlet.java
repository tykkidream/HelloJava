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

/**
 * 一个长简单的Portlet。
 * 
 * @author Tykkidream
 *
 */
public class HelloWorldPortlet extends GenericPortlet {
	private static final String jsp_view = "/WEB-INF/views/helloworld/view.jsp";
	private static final String jsp_edit = "/WEB-INF/views/helloworld/edit.jsp";
	private static final String jsp_help = "/WEB-INF/views/helloworld/help.jsp";
	
	@Override
	public void init(PortletConfig config) throws PortletException {
		super.init(config);
	}
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		response.setContentType(request.getResponseContentType());

		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(jsp_view);
		rd.include(request,response);
	}
	
	@Override
	protected void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		response.setContentType(request.getResponseContentType());

		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(jsp_edit);
		rd.include(request,response);
	}
	
	@Override
	protected void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		response.setContentType(request.getResponseContentType());

		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(jsp_help);
		rd.include(request,response);
	}
	
	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
		super.processAction(request, response);
	}
}
