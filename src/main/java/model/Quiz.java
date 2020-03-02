package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Expose(serialize = true, deserialize = true)
	private long id;
	@Column(name = "name")
	@Expose(serialize = true, deserialize = true)
	private String name;
	@Column(name = "pictureURL")
	@Expose(serialize = true, deserialize = true)
	private String pictureURL;
	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "quiz")
	private Set<Feedback> feedback;
	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "quiz")
	private Set<QuestionPool> quizPool;


	
	public Quiz() {}
	public Quiz(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Feedback> getFeedback() {
		return feedback;
	}
	public void setFeedback(Set<Feedback> feedback) {
		this.feedback = feedback;
	}
	public Set<QuestionPool> getQuizPool() {
		return quizPool;
	}
	public void setQuizPool(Set<QuestionPool> quizPool) {
		this.quizPool = quizPool;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Quiz quiz = (Quiz) o;
		return id == quiz.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
