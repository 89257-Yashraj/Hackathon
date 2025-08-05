package com.sunbeam.dto;

import com.sunbeam.entities.Difficulty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddInterviewQuestionDto {
	private String question;
	private String answer;
	private Difficulty difficulty;
}
