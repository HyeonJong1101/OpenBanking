package kr.ac.kopo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	
	private HandlerMapping mappings = new HandlerMapping();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String uri2 = request.getContextPath(); //라이브러리이름
		
		uri = uri.substring(uri2.length());
		//System.out.println("uri :"+uri );
		//System.out.println("uri2 :"+uri2 );
		Controller ctrl = mappings.getController(uri);
		
		String callPage = ctrl.handleRequest(request, response);
		//System.out.println("jsp : " + callPage);
		
		
		if(callPage.startsWith("redirect:")) {
			
			response.sendRedirect(callPage.substring("redirect:".length()));
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
			dispatcher.forward(request, response);
			
		}
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String propLoc = config.getInitParameter("propLoc");
		System.out.println(propLoc);
		
		mappings = new HandlerMapping(propLoc);
	}

	
}
