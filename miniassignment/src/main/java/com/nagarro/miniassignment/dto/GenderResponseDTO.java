package com.nagarro.miniassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenderResponseDTO {
	private int count;
	private String name;
	private String gender;
	private double probability;
}