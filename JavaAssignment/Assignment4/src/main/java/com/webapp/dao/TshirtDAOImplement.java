package com.webapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.webapp.model.Tshirt;

@Repository
public class TshirtDAOImplement implements TshirtDAO {
	@Autowired
	private SessionFactory sessionFactory;


	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void saveTshirt(Tshirt tshirt) {
		getSession().saveOrUpdate(tshirt);
	}

	public List<Tshirt> searchTshirts(String colour, String size, String gender, String sortBy) {
		if (colour == null || colour.isEmpty() || size == null || size.isEmpty() || gender == null || gender.isEmpty()
				|| sortBy == null || sortBy.isEmpty()) {
			throw new IllegalArgumentException("color size gender outputPreference parameters cannot be null or empty");
		}

		String hql = "FROM Tshirt WHERE colour = :color AND size = :size AND genderRecommendation = :gender ";
		if (sortBy.equalsIgnoreCase("Price")) {
			hql += "order by price";
		} else if (sortBy.equalsIgnoreCase("Rating")) {
			hql += "order by rating";
		} else if (sortBy.equalsIgnoreCase("Both")) {
			hql += "order by price,rating";
		} else {
			System.err.println("Invalid output preference: " + sortBy);
		}

		Query query = getSession().createQuery(hql);
		query.setParameter("color", colour);
		query.setParameter("size", size);
		query.setParameter("gender", gender);
		System.out.println("Final Query: " + query.getQueryString());

		List<Tshirt> results = query.list();

		System.out.println("Tshirts Fetched");
		return results;
	}


}
