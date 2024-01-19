package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Product;
import util.HibernateUtils;


/**
 * Servlet implementation class AddProductServlet
 */
@MultipartConfig(maxFileSize = 1024 * 1024) // 1 MB
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		String quantityStr = request.getParameter("quantity");
		String size = request.getParameter("size");
		Part imagePart = request.getPart("image");

		// convert quantity to integer
		int quantity = Integer.parseInt(quantityStr);

		// save image file to disk and get the filename
		String imageName = null;
		try {
			if (imagePart != null && imagePart.getSize() > 0) {
				imageName = saveImage(imagePart);
			}
		}
		catch(IOException e) {
			response.setContentType("text/html");
			response.getWriter().print("<html><body>");
			response.getWriter().print("<h1>Upload folder size limit exceeded</h1>");
			response.getWriter().print("<p>The maximum allowed size for uploads has been exceeded. Please try again with a smaller file size.</p>");
			response.getWriter().print("<a href='" + request.getContextPath() + "/productmanagement.jsp'>Back to Product Management</a>");
			response.getWriter().print("</body></html>");

			return ;
//			response.sendRedirect(request.getContextPath() + "/productmanagement.jsp");
		}
		

		// create a new Product object and set its properties
		Product product = new Product();
		product.setTitle(title);
		product.setQuantity(quantity);
		product.setSize(size);
		product.setImageFileName(imageName);

		System.out.println("Product obj Created");

		// save the product to the database using Hibernate
 		Session session = HibernateUtils.getSessionfactory().openSession();
		Transaction tx =  null;
		try {
			tx = session.beginTransaction();
			session.save(product);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			session.close();
		}
		
		System.out.println("saved the product to the database using Hibernate");
        response.sendRedirect(request.getContextPath() + "/productmanagement.jsp");
	}

	
	// Function to Upload product Image 
	private String saveImage(Part imagePart) throws IOException {
		String fileName = UUID.randomUUID().toString() + ".jpg";
		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		System.out.println(uploadPath);
		
		
		
		
		 long totalSize = 0;
		    for (File file : uploadDir.listFiles()) {
		        if (file.isFile()) {
		            totalSize += file.length();
		        }
		    }

		    if (totalSize + imagePart.getSize() > 10 * 1024 * 1024) { // 10MB
		        throw new IOException("Upload folder size limit exceeded");
		    }
		    
		
		String filePath = uploadPath + File.separator + fileName;
		System.out.println(filePath);
		imagePart.write(filePath);
		return fileName;
	}

}
