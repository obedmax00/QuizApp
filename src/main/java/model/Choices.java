package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "choices")
public class Choices {
	@Id
	@GeneratedValue
	@Expose(serialize = true, deserialize = true)

	@Column(name = "id")
	private long id;
	@Expose(serialize = true, deserialize = true)
	@Column(name = "choiceNumber" )
	private String choiceNumber;
	@Expose(serialize = true, deserialize = true)
	@Column(insertable = false, updatable = false)
	private long questionId;
	@Expose(serialize = true, deserialize = true)

	@Column(name = "choiceName")
	private String choiceName;
	@Expose(serialize = true, deserialize = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionId")
	private QuestionPool questionPool;
	public Choices() {}
	
	public Choices(String choiceNumber, long questionId, String choiceName) {
		super();
		this.choiceNumber = choiceNumber;
		this.questionId = questionId;
		this.choiceName = choiceName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public QuestionPool getQuestionPool() {
		return questionPool;
	}

	public void setQuestionPool(QuestionPool questionPool) {
		this.questionPool = questionPool;
	}

	public String getChoiceNumber() {
		return choiceNumber;
	}
	public void setChoiceNumber(String choiceNumber) {
		this.choiceNumber = choiceNumber;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getChoiceName() {
		return choiceName;
	}
	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}
	
	
}
