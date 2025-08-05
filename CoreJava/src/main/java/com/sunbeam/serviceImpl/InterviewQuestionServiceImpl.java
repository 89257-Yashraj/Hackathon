package com.sunbeam.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sunbeam.Dao.InterviewQuestionDao;
import com.sunbeam.customException.InvalidInputException;
import com.sunbeam.dto.AddInterviewQuestionDto;
import com.sunbeam.dto.InterviewQuestionDto;
import com.sunbeam.entities.InterviewQuestion;
import com.sunbeam.entities.SubTopics;
import com.sunbeam.service.InterviewQuestionService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InterviewQuestionServiceImpl implements InterviewQuestionService {

	private final InterviewQuestionDao iqDao;
	private final ModelMapper modelMapper;
	@Override
	public List<InterviewQuestionDto> getIqBySubTopicId(Long id) {
		
		return iqDao.findBysubTopicId(id).stream()
		.map(iq->modelMapper.map(iq, InterviewQuestionDto.class))
		.toList();
	}
	@Override
	public String addNewInterviewQuestion(AddInterviewQuestionDto newInterviewQuestion, Long subTopicId) {
		
		SubTopics st = iqDao.findSubTopicById(subTopicId);
		if(st!=null) {
			
			InterviewQuestion question = iqDao.findByInterviewQuestionAndSubTopicId(newInterviewQuestion.getQuestion(), subTopicId);
			if(question!=null) {
				
				throw new InvalidInputException("Question already Exists for given sub-topic");
			}
			InterviewQuestion iq = modelMapper.map(newInterviewQuestion, InterviewQuestion.class);
			iq.setSubTopic(st);
			st.getInterviewQuestions().add(iq);
			iqDao.save(iq);
			return "Question Added in the Interview Question list";
		} else {
			
			throw new InvalidInputException("Sub Topic with given id is not present");
		}
	}
}
