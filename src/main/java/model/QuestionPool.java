package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questionPool")
public class QuestionPool {
	@Id
	@Column(name = "id")
	@GeneratedValue
	@Expose(serialize = true, deserialize = true)
	private long id;
	@Expose(serialize = true, deserialize = true)
	@Column(insertable = false, updatable = false)
	private long quizId;
	@Expose(serialize = true, deserialize = true)
	@Column(name = "question")
	private String question;
	@Expose(serialize = true, deserialize = true)
	@Column(name = "questionAns")
	private String questionAns;
	@Expose(serialize = true, deserialize = true)
	@Column(name = "ifShort")
	private boolean ifShort;
	@Expose(serialize = true, deserialize = true)
	@Column(name = "status")
	private int status;
	@Expose(serialize = true, deserialize = true)
	@OneToMany(mappedBy = "questionPool")
	private Set<Choices> choices;

	@Expose(serialize = true, deserialize = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizId")
	private Quiz quiz;

	@OneToOne(mappedBy = "questionPool")
	//@Column(updatable = false,insertable = false)
	private QuizHistoryAnswer Answer;

	public QuizHistoryAnswer getAnswer() {
		return Answer;
	}

	public void setAnswer(QuizHistoryAnswer answer) {
		Answer = answer;
	}



	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuizId() {
		return quizId;
	}
	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionAns() {
		return questionAns;
	}
	public void setQuestionAns(String questionAns) {
		this.questionAns = questionAns;
	}
	public boolean isIfShort() {
		return ifShort;
	}
	public void setIfShort(boolean ifShort) {
		this.ifShort = ifShort;
	}
	public Set<Choices> getChoices() {
		return choices;
	}
	public void setChoices(Set<Choices> choices) {
		this.choices = choices;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((questionAns == null) ? 0 : questionAns.hashCode());
		result = prime * result + (int) (quizId ^ (quizId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionPool other = (QuestionPool) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionAns == null) {
			if (other.questionAns != null)
				return false;
		} else if (!questionAns.equals(other.questionAns))
			return false;
		if (quizId != other.quizId)
			return false;
		return true;
	}
	
}
