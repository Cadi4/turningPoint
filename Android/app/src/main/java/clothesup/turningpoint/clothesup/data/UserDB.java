package clothesup.turningpoint.clothesup.data;

import java.util.ArrayList;

/**
 * Created by user on 2016-05-26.
 */
public class UserDB {
    private int userId;
    private ArrayList<String> email;
    private String nickname;
    private String profile_img;
    private ArrayList<String> style;
    private ArrayList<String> fav_store;
    private ArrayList<Integer> fav_reviewer;
    private ArrayList<String> visit_store;
    private ArrayList<Integer> good_review;

    public UserDB(int userId, ArrayList<String> email, String nickname, String profile_img,
                  ArrayList<String> style, ArrayList<String> fav_store,
                  ArrayList<Integer> fav_reviewer, ArrayList<String> visit_store,
                  ArrayList<Integer> good_review) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.profile_img = profile_img;
        this.style = style;
        this.fav_store = fav_store;
        this.fav_reviewer = fav_reviewer;
        this.visit_store = visit_store;
        this.good_review = good_review;
    }

    public int getUserId() {
        return userId;
    }

    public ArrayList<String> getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public ArrayList<String> getStyle() {
        return style;
    }

    public ArrayList<String> getFav_store() {
        return fav_store;
    }

    public ArrayList<Integer> getFav_reviewer() {
        return fav_reviewer;
    }

    public ArrayList<String> getVisit_store() {
        return visit_store;
    }

    public ArrayList<Integer> getGood_review() {
        return good_review;
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "userId=" + userId +
                ", email=" + email +
                ", nickname='" + nickname + '\'' +
                ", profile_img='" + profile_img + '\'' +
                ", style=" + style +
                ", fav_store=" + fav_store +
                ", fav_reviewer=" + fav_reviewer +
                ", visit_store=" + visit_store +
                ", good_review=" + good_review +
                '}';
    }
}
