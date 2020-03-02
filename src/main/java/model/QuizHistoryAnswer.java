package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quizHistoryAnswer")
public class QuizHistoryAnswer {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Expose(serialize = true, deserialize = true)
	private long id;
	@Column(insertable = false, updatable = false)
	@Expose(serialize = true, deserialize = true)
	private long quizHistoryId;
	@Column(insertable = false, updatable = false)
	@Expose(serialize = true, deserialize = true)
	private long questionId;
	@Column(name = "userAns")
	@Expose(serialize = true, deserialize = true)
	private String userAns;
	@Column(name = "isCorrect")
	@Expose(serialize = true, deserialize = true)
	private boolean isCorrect;

	@Expose(serialize = true, deserialize = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizHistoryId")
	private QuizHistory quizHistory;

	@Expose(serialize = true, deserialize = true)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionId")
	private QuestionPool questionPool;


	public QuestionPool getQuestionPool() {
		return questionPool;
	}

	public void setQuestionPool(QuestionPool questionPool) {
		this.questionPool = questionPool;
	}

	public QuizHistory getQuizHistory() {
		return quizHistory;
	}

	public void setQuizHistory(QuizHistory quizHistory) {
		this.quizHistory = quizHistory;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuizHistoryId() {
		return quizHistoryId;
	}
	public void setQuizHistoryId(long quizHistoryId) {
		this.quizHistoryId = quizHistoryId;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getUserAns() {
		return userAns;
	}
	public void setUserAns(String userAns) {
		this.userAns = userAns;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}


	
}
