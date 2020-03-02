package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "websiteFeedback")
public class WebsiteFeedback {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @Expose(serialize = true, deserialize = true)
    private long id;
    @Column(name = "feedback")
    @Expose(serialize = true, deserialize = true)
    private String feedback;
    @Column(name = "rating")
    @Expose(serialize = true, deserialize = true)
    private String rating;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
