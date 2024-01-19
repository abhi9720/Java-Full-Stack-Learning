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

import dao.ProductDao;
import model.Product;

/**
 * Servlet implementation class EditProductServlet
 */
@MultipartConfig(maxFileSize = 1024 * 1024) // 1 MB
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));
	        Product product = ProductDao.getProduct(id);
	        request.setAttribute("product", product);
	        request.getRequestDispatcher("/edit-product.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
		
        String title = request.getParameter("title");
		String quantityStr = request.getParameter("quantity");
		String size = request.getParameter("size");
		Part imagePart = request.getPart("image");
        Product product = ProductDao.getProduct(id);
        
		int quantity = Integer.parseInt(quantityStr);

        
         //  save new image file to disk and get the filename
     		String imageName = null;
     		System.out.println(imagePart.getSize()+"  "+imagePart);
     		if (imagePart != null && imagePart.getSize() > 0) {
     			
     			// delete previous image file 
     			String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
     	        String fileName = product.getImageFileName();
     	        System.out.println("Edit : =>>>> "+fileName);
     	        File file = new File(uploadPath + File.separator + fileName);
     	        if (file.exists()) {
     	           file.delete();
     	           System.out.println("EDIT: File deleted Successfully!!!");
     	        }

     	        
     	        
     	        
     			imageName = saveImage(imagePart);
     			product.setImageFileName(imageName);
     		}
     		
     		
     		product.setTitle(title);
    		product.setQuantity(quantity);
    		product.setSize(size);
    		
    		ProductDao.updateProduct(product);
    
            response.sendRedirect(request.getContextPath() + "/productmanagement.jsp");


		
		
	}

	
	private String saveImage(Part imagePart) throws IOException {
		String fileName = UUID.randomUUID().toString() + ".jpg";
		String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		System.out.println(uploadPath);
		String filePath = uploadPath + File.separator + fileName;
		System.out.println(filePath);
		imagePart.write(filePath);
		return fileName;
	}
	
}
