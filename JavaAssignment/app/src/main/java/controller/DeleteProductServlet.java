package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;

/**
 * Servlet implementation class DeleteProductServlet
 */
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (!(session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn"))) {
			response.sendRedirect("login.jsp");
			return ;
		}
		
        Long id = Long.parseLong(request.getParameter("id"));
        
		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        String fileName = ProductDao.getProduct(id).getImageFileName();
        System.out.println("=>>>> "+fileName);
        File file = new File(uploadPath + File.separator + fileName);
        if (file.exists()) {
           file.delete();
           System.out.println("File deleted Successfully!!!");
        }

        
        ProductDao.deleteProduct(id);
        response.sendRedirect(request.getContextPath() + "/productmanagement.jsp");
    }

}
