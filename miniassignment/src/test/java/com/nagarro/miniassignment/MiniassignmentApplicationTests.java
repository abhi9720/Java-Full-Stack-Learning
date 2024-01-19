package com.nagarro.miniassignment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiniassignmentApplicationTests {

	 @Autowired
	    private MiniassignmentApplication application;


	@Test
	void contextLoads() {
        assertNotNull(application);

	}

}
