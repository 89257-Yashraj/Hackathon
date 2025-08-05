package com.sunbeam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.AddInterviewQuestionDto;
import com.sunbeam.dto.InterviewQuestionDto;
import com.sunbeam.service.InterviewQuestionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class InterviewQuestionController {

	private final InterviewQuestionService iqService;
	
	@GetMapping("/interviewQuestion")
	public ResponseEntity<?> getIqBySubTopicId(@RequestParam Long id) {
		
		List<InterviewQuestionDto> iqList = iqService.getIqBySubTopicId(id);
		if(iqList.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(iqList);
	}
	@PostMapping("/addInterviewQuestion")
	public String addaddInterviewQuestion(@RequestBody AddInterviewQuestionDto newInterviewQuestion, @RequestParam Long subTopicId) {
		
		return iqService.addNewInterviewQuestion(newInterviewQuestion, subTopicId);
	}
}
