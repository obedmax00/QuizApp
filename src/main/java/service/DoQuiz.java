package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DAO.ChoicesDAO;
import DAO.QuestionPoolDAO;
import DAO.QuizHistoryAnswerDAO;
import DAO.QuizHistoryDAO;
import model.QuestionPool;
import model.QuizHistory;
import model.QuizHistoryAnswer;

public class DoQuiz {
	private List<QuestionPool> questionList;
	private int curQuestion=0;
	private final int TOTALQUESTION=10;
	private List<QuizHistoryAnswer> answers;
	public double timeTook=0;
	private String startTime;
	private String endTime;

	
	
	public DoQuiz() {
		answers = new ArrayList<QuizHistoryAnswer>();
	}
	
	public void setUp(long quizId) {
		QuestionPoolDAO questionPoolDAO = new QuestionPoolDAO();
		ChoicesDAO choicesDAO = new ChoicesDAO();
		//get all question and choices
		questionList = questionPoolDAO.getTenQuestionByQuizId(quizId,10);
		for(QuestionPool x: questionList) {
			long questionId = x.getId();
			x.setChoices(choicesDAO.getChoicesByQuestionId(questionId));
		}
		

	}
	
	public QuestionPool nextQuestion(String userAnswer) {
		QuizHistoryAnswer answer = new QuizHistoryAnswer();
		Boolean flag = questionList.get(curQuestion).getQuestionAns().equals(userAnswer);

		answer.setCorrect(flag);
		long questionId = questionList.get(curQuestion).getId();
		answer.setQuestionId(questionId);
		answer.setUserAns(userAnswer);
		answer.setUserAns(userAnswer);

		questionList.get(curQuestion).setAnswer(answer);

		int index = findIfQuestionInList(questionId);
		if(index>=0) {
			answers.remove(index);
	
		}
			answers.add(answer);

		curQuestion++;
		if(curQuestion>9) {
			curQuestion=9;
		}
		return questionList.get(curQuestion);
	}
	
	
	public QuestionPool saveCurQuestion(String userAnswer) {
		QuizHistoryAnswer answer = new QuizHistoryAnswer();
		Boolean flag = questionList.get(curQuestion).getQuestionAns().equals(userAnswer);
		answer.setCorrect(flag);
		
		long questionId =questionList.get(curQuestion).getId();
		answer.setQuestionId(questionId);
		answer.setUserAns(userAnswer);
		
		questionList.get(curQuestion).setAnswer(answer);
		int index = findIfQuestionInList(questionId);
		if(index>=0) {

			answers.remove(index);
		

		}
		answers.add(answer);
		return questionList.get(curQuestion);
	}
	
	public QuestionPool previousQuestion(String userAnswer) {
		QuizHistoryAnswer answer = new QuizHistoryAnswer();
		Boolean flag = questionList.get(curQuestion).getQuestionAns().equals(userAnswer);
		answer.setCorrect(flag);
		
		long questionId =questionList.get(curQuestion).getId();
		answer.setQuestionId(questionId);
		answer.setUserAns(userAnswer);
		
		questionList.get(curQuestion).setAnswer(answer);
		int index = findIfQuestionInList(questionId);
		if(index>=0) {

			answers.remove(index);
		

		}
		answers.add(answer);
		curQuestion--;
		if(curQuestion<0) {
			curQuestion =0;
		}
		return questionList.get(curQuestion);
	}
	
	public QuestionPool chooseQuestion(String userAnswer,int questionNumber) {
		
		QuizHistoryAnswer answer = new QuizHistoryAnswer();
		Boolean flag = questionList.get(curQuestion).getQuestionAns().equals(userAnswer);
		answer.setCorrect(flag);
		
		long questionId =questionList.get(curQuestion).getId();
		answer.setQuestionId(questionId);
		answer.setUserAns(userAnswer);
		
		questionList.get(curQuestion).setAnswer(answer);
		int index = findIfQuestionInList(questionId);
		if(index>=0) {

			answers.remove(index);
		

		}
		answers.add(answer);
		curQuestion = questionNumber-1;
		return questionList.get(curQuestion);
		
	}
	
	public QuestionPool currentQuestion() {
		return questionList.get(curQuestion);
	}
	
	public int findIfQuestionInList(long questionId) {
		for(int i = 0; i<answers.size();i++) {
			if(answers.get(i).getQuestionId()==questionId) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	public void submit(long userId, long quizId) {
		
		QuizHistory history = new QuizHistory();
		history.setUserId(userId);
		history.setQuizId(quizId);
		history.setStartTime(startTime);
		history.setEndTime(endTime);
		history.setScore(correct());
		
		
		history.setPass(ifPass());
	
		QuizHistoryDAO historyDAO = new QuizHistoryDAO();
		historyDAO.setHistory(history);
		long historyId = historyDAO.getHistoryID(history);
		
		
		
		QuizHistoryAnswerDAO answerDAO = new QuizHistoryAnswerDAO();
		answerDAO.setAnswers(answers, historyId);
	}

	public int getCurQuestion() {
		return curQuestion;
	}
	
	
	public QuizHistoryAnswer getCurAnswer(long questionId) {
		for(int i=0; i<answers.size();i++) {
			if(answers.get(i).getQuestionId()==questionId)
				return answers.get(i);
		}
		
		return null;
	}
	
	public boolean ifPass(){
		int correctNum= 0;
		for(int i=0;i<questionList.size();i++) {
			for(int j=0;j<answers.size();j++)
				if(questionList.get(i).getId()==answers.get(j).getQuestionId()
				&& questionList.get(i).getQuestionAns().equals(answers.get(j).getUserAns()))
					correctNum++;
		}

		return correctNum<6?false:true;
	}

	public int correct(){
		int correctNum= 0;
		for(int i=0;i<questionList.size();i++) {
			for(int j=0;j<answers.size();j++)
				if(questionList.get(i).getId()==answers.get(j).getQuestionId()
						&& questionList.get(i).getQuestionAns().equals(answers.get(j).getUserAns()))
					correctNum++;
		}

		return correctNum;
	}



	public int getAnswerSize(){
		int counter = 0;
		for(int i = 0;i<answers.size();i++) {
			if(answers.get(i).getUserAns()!=null) {
				counter++;
			}
		}
		return counter;
	}
	
	public boolean answered(int questionNumber) {
		QuestionPool question = questionList.get(questionNumber-1);
			for(int j=0;j<answers.size();j++) {
				if(question.getId()==answers.get(j).getQuestionId()
				&& answers.get(j).getUserAns()!=null) {
					return true;
					}
			}
		return false;
	}
	
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<QuestionPool> getQuestionList() {
		return questionList;
	}


	public List<QuizHistoryAnswer> getAnswers() {
		return answers;
	}


}


























































