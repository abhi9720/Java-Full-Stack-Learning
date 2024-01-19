package com.webapp.service;

import java.util.List;
import com.webapp.model.Tshirt;

public interface TshirtService {
	
	public void saveOrUpate(Tshirt tshirt);
    public List<Tshirt> searchTshirts(String colour, String size, String gender, String sortBy);
 

}
