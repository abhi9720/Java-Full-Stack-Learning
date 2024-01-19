package com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.TshirtDAO;
import com.webapp.model.Tshirt;

@Service
@Transactional
public class TshirtServiceImpl implements TshirtService {

	TshirtDAO tshirtdao;

	@Autowired
	public void setTshirtDAO(TshirtDAO tshirtdao) {
		this.tshirtdao = tshirtdao;
	}

	public List<Tshirt> searchTshirts(String colour, String size, String gender, String sortBy) {
		return tshirtdao.searchTshirts(colour, size, gender, sortBy);
	}

	public void saveOrUpate(Tshirt tshirt) {
		tshirtdao.saveTshirt(tshirt);
	}

}
