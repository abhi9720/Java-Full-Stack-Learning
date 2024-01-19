package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ProductDao;
import model.Product;


public class PoductManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println(
				"-----------------here -----" + session.getAttribute("loggedIn") + "------------------------------");
		if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
			response.sendRedirect(request.getContextPath() + "/productmanagement.jsp");
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}
	
	

}
