package com.webapp.dao;

import java.util.List;

import com.webapp.model.Tshirt;

public interface TshirtDAO {

	public void saveTshirt(Tshirt tshirt );

	public List<Tshirt> searchTshirts(String colour, String size, String gender, String sortBy);


}
