package clothesup.turningpoint.clothesup.data;

import java.util.ArrayList;

/**
 * Created by bro Jo on 2016-05-26.
 */
public class ReviewDB {
    private int reviewId;
    private int userId;
    private String id;
    private String nickname;
    private float grade;
    private ArrayList<String> style;
    private String photo;
    private String text;
    private String register_time;

    public ReviewDB(int reviewId, int userId, String id, String nickname,
                    float grade, ArrayList<String> style, String photo,
                    String text, String register_time) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.id = id;
        this.nickname = nickname;
        this.grade = grade;
        this.style = style;
        this.photo = photo;
        this.text = text;
        this.register_time = register_time;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public float getGrade() {
        return grade;
    }

    public ArrayList<String> getStyle() {
        return style;
    }

    public String getPhoto() {
        return photo;
    }

    public String getText() {
        return text;
    }

    public String getRegister_time() {
        return register_time;
    }

    @Override

    public String toString() {
        return "ReviewDB{" +
                "reviewId=" + reviewId +
                ", userId=" + userId +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", grade=" + grade +
                ", style=" + style +
                ", photo='" + photo + '\'' +
                ", text='" + text + '\'' +
                ", register_time='" + register_time + '\'' +
                '}';
    }
}
