package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Expose(serialize = true, deserialize = true)
	private long id;
	@Expose(serialize = true, deserialize = true)
	@Column(insertable = false, updatable = false)
	private long quizId;
	@Expose(serialize = true, deserialize = true)
	@Column(name ="feedback")
	private String feedback;
	@Expose(serialize = true, deserialize = true)
	@Column(name = "rating")
	private String rating;
	@Expose(serialize = true, deserialize = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quizId")
	private Quiz quiz;
	
	public Feedback() {}
	
	public Feedback(long id, long quizId, String feedback, String rating) {
		super();
		this.id = id;
		this.quizId = quizId;
		this.feedback = feedback;
		this.rating = rating;
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
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
