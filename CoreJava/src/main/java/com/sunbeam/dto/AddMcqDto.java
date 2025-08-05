package com.sunbeam.dto;

import com.sunbeam.entities.Difficulty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMcqDto {

	private String question;
	private String option_a;
	private String option_b;
	private String option_c;
	private String option_d;
	private String answer;
	private Difficulty difficulty;
}
