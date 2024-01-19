package com.nagarro.miniassignment.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String gender;

	@JsonProperty("name")
	private NameDTO name;

	@JsonProperty("dob")
	private DateOfBirthDTO dob;

	@JsonProperty("nat")
	private String nationality;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class NameDTO {
		private String first;
		private String last;

	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DateOfBirthDTO {
		private  Date date;
		private  int age;

	}


	public String getFullName() {
		return this.getName().getFirst()+" "+this.getName().getLast();
	}

}
