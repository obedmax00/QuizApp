package model;

import com.google.gson.annotations.Expose;
import org.hibernate.LazyInitializationException;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "quizUser")
public class QuizUser {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@Expose(serialize = true, deserialize = true)
	private long id;
	@Column(name = "name")
	@Expose(serialize = true, deserialize = true)
	private String name;
	@Column(name = "password")
	@Expose(serialize = true, deserialize = true)
	private String password;
	@Column(name = "role")
	@Expose(serialize = true, deserialize = true)
	private String role;
	@Column(name = "Email")
	@Expose(serialize = true, deserialize = true)
	private String Email;
	@Column(name = "status")
	@Expose(serialize = true, deserialize = true)
	private int status;
	@Column(name = "phoneNumber")
	@Expose(serialize = true, deserialize = true)
	private String phoneNumber;
	@Column(name = "firstName")
	@Expose(serialize = true, deserialize = true)
	private String firstName;
	@Column(name = "lastName")
	@Expose(serialize = true, deserialize = true)
	private String lastName;
	@Column(name ="address")
	@Expose(serialize = true, deserialize = true)
	private String address;

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "quizUser")
	private Set<QuizHistory> quizHistory;
	
	public QuizUser() {}
	public QuizUser(long id, String name, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Set<QuizHistory> getQuizHistory() {
		try{
			quizHistory.size();
		}catch(Exception e){
			return null;
		}
		return quizHistory;

	}

	public void setQuizHistory(Set<QuizHistory> quizHistory) {
		this.quizHistory = quizHistory;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "QuizUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				", Email='" + Email + '\'' +
				", status=" + status +
				", phoneNumber='" + phoneNumber + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QuizUser quizUser = (QuizUser) o;
		return id == quizUser.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
