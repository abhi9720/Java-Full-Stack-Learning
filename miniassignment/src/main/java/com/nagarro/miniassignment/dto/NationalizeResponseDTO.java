package com.nagarro.miniassignment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NationalizeResponseDTO {
	private int count;
	private String name;
	private List<CountryProbability> country;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CountryProbability {
		private String country_id;
		private double probability;
	}
}
