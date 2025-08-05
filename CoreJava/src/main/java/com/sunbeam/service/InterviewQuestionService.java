package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.AddInterviewQuestionDto;
import com.sunbeam.dto.InterviewQuestionDto;

public interface InterviewQuestionService {

	List<InterviewQuestionDto> getIqBySubTopicId(Long id);
	String addNewInterviewQuestion(AddInterviewQuestionDto newInterviewQuestion, Long subTopicId);
}
