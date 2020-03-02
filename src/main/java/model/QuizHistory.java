package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "quizHistory")
public class QuizHistory {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Expose(serialize = true, deserialize = true)
	private long id;

	@Column(insertable = false,updatable = false)
	@Expose(serialize = true, deserialize = true)
	private long userId;
	@Column(insertable = false,updatable = false)
	@Expose(serialize = true, deserialize = true)
	private long quizId;
	@Column(name = "isPass")
	@Expose(serialize = true, deserialize = true)
	private boolean isPass;
	@Column(name = "startTime")
	@Expose(serialize = true, deserialize = true)
	private String startTime;
	@Column(name = "endTime")
	@Expose(serialize = true, deserialize = true)
	private String endTime;
	@Column(name = "score")
	@Expose(serialize = true, deserialize = true)
	private Integer score;

	@OneToMany(mappedBy = "quizHistory")
	@Expose(serialize = false, deserialize = false)
	private  Set<QuizHistoryAnswer> historyAnswers;
//transient

	@Expose(serialize = true, deserialize = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private QuizUser quizUser;


	@Expose(serialize = true, deserialize = true	)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizId")
	private Quiz quiz;


	public QuizUser getQuizUser() {
		return quizUser;
	}

	public void setQuizUser(QuizUser quizUser) {
		this.quizUser = quizUser;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getQuizId() {
		return quizId;
	}
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Set<QuizHistoryAnswer> getHistoryAnswers() {
		return historyAnswers;
	}

	public void setHistoryAnswers(Set<QuizHistoryAnswer> historyAnswers) {
		this.historyAnswers = historyAnswers;
	}

	@Override
	public String toString() {
		return "QuizHistory{" +
				"userId=" + userId +
				", quizId=" + quizId +
				", isPass=" + isPass +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", score=" + score +
				'}';
	}


}
