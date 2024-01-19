package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Product;
import util.HibernateUtils;

public class ProductDao {
	
	public static void saveProduct(Product product) {
		Transaction tx =  null;
		Session session  =  HibernateUtils.getSessionfactory().openSession();
		try{
			tx =  session.beginTransaction();
			session.save(product);
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	
	public static void updateProduct(Product product) {
		Transaction tx =  null;
		Session session  =  HibernateUtils.getSessionfactory().openSession();
		try{
			tx =  session.beginTransaction();
			session.update(product);
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	
	public static void deleteProduct(Long id) {
		Transaction tx =  null;
		Session session  =  HibernateUtils.getSessionfactory().openSession();
		try{
			tx =  session.beginTransaction();
			Product product  =  (Product) session.get(Product.class, id);
			if(product!=null) {
				session.delete(product);
				System.out.println("Product Deleted Successfully");
			}
			
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public static Product getProduct(Long id) {
		Product product =  null;
		Transaction tx =  null;
		Session session  =  HibernateUtils.getSessionfactory().openSession();
		try{
			tx =  session.beginTransaction();
			product =  (Product) session.get(Product.class, id);
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return product;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Product> getAllProduct(){
		List<Product> productList =  null;
		Transaction tx =  null;
		Session session  =  HibernateUtils.getSessionfactory().openSession();
		try{
			tx =  session.beginTransaction();
			System.out.println("--------------------------------");
			System.out.println(session.createQuery("from Product").list());
			productList =  session.createQuery("from Product").list();
			System.out.println("-----------------Recived Product ----------------");
			tx.commit();
		}
		catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			System.out.println("-----------------Error Occured ----------------");

			e.printStackTrace();
		}
		finally {
			System.out.println("-----------------Session Close ----------------");

			session.close();
		}
		System.out.println("-----------------All product returned ----------------");
		return productList;
	}
	
	
}
