package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.user;
import util.HibernateUtils;

/**
 * Servlet implementation class loginController
 */
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Display the login form

		HttpSession session = request.getSession();
		if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
			response.sendRedirect(request.getContextPath() + "/productmanagement.jsp");
			return;
		}
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Create a Hibernate session factory
		SessionFactory sessionFactory = HibernateUtils.getSessionfactory();

		// Get a new session
		Session session = sessionFactory.openSession();

		// Use HQL to query the database for the user
		Query query = session.createQuery("FROM user WHERE username = :username AND password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);

		user user = (user) query.uniqueResult();

		// Close the session
		session.close();

		// If the user exists, forward to the home page
		if (user != null) {
			// create a new session
			HttpSession usersession = request.getSession();
			// set session attribute
			usersession.setAttribute("user", username);
			usersession.setAttribute("loggedIn", true);
			
			
			
			

			// redirect to the home page
			response.sendRedirect("productmanagement.jsp");
		} else {
			// If the user does not exist, display an error message
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
