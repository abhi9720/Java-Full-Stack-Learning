package com.nagarro.miniassignment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.miniassignment.dto.UserListResponseDTO;
import com.nagarro.miniassignment.service.UserService;
import com.nagarro.miniassignment.util.UserValidationHelper;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    

    @PostMapping
    public ResponseEntity<?> createUsers(@RequestBody Map<String, Object> payload)  {
    	        System.out.println(payload);
    	        int size = UserValidationHelper.extractSizeFromPayload(payload);
    	        UserValidationHelper.validateSizeParameter(size);

    	        return new ResponseEntity<>(userService.createUsers(size), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(name = "sortType") String sortType,
            @RequestParam(name = "sortOrder") String sortOrder, @RequestParam(name = "limit") String limitParam,
            @RequestParam(name = "offset") String offsetParam) {
    	
        UserValidationHelper.validateInputParameters(sortType, sortOrder, limitParam, offsetParam);
        int limit = Integer.parseInt(limitParam);
        int offset = Integer.parseInt(offsetParam);
        UserListResponseDTO users = userService.getUsers(sortType, sortOrder, limit, offset);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
