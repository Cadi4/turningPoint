package clothesup.turningpoint.clothesup.network;

import java.util.List;

import clothesup.turningpoint.clothesup.data.ReviewDB;
import clothesup.turningpoint.clothesup.data.UserDB;
import clothesup.turningpoint.clothesup.data.ContentDB;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bro Jo on 2016-05-06
 */
public interface MappingService {
    // Store
    @GET("/stores")
    Call<List<ContentDB>> getServerStores();

    @GET("/stores/{name}")
    Call<List<ContentDB>> findServerStoreByName(
            @Path("name") String storeName
    );

    @GET("/storesId/{id}")
    Call<List<ContentDB>> findServerStoreById(
            @Path("id") String storeId
    );

    @GET("/storesLocation/{clickedX}/{clickedY}")
    Call<List<ContentDB>> findServerStoreByLocation(
            @Path("clickedX") int clickedX,
            @Path("clickedY") int clickedY
    );

    // User
    @GET("/users")
    Call<List<UserDB>> getServerUsers();

    @GET("/usersById/{id}")
    Call<List<UserDB>> findUserById(
            @Path("id") int id
    );

    @GET("/usersByNickname/{nick}")
    Call<List<UserDB>> findUserByNickname(
            @Path("nick") String nickname
    );

    @GET("/usersByEmail/{email}")
    Call<List<UserDB>> findUserByEmail(
            @Path("email") String email
    );

    // Review
    @GET("/reviews")
    Call<List<ReviewDB>> getServerReviews();

    @GET("/reviewsByStore/{storeId}")
    Call<List<ReviewDB>> findReviewsByStore(
            @Path("storeId") String storeId
    );

    @GET("/reviewsByUserId/{userId}")
    Call<List<ReviewDB>> findReviewsByUserId(
            @Path("userId") int userId
    );

    @GET("/reviewsByReviewId/{reviewId}")
    Call<List<ReviewDB>> findReviewsByReviewId(
            @Path("reviewId") int reviewId
    );
}
