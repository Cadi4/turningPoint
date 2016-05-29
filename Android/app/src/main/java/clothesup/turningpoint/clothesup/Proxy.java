package clothesup.turningpoint.clothesup;

import android.util.Log;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bro Jo on 2016-05-06.
 */
public class Proxy {
    MappingService service;

    public Proxy(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://52.196.54.163:1337")
                                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(MappingService.class);
    }

    // Store
    public List<ContentDB> getServerStores(){
        Call<List<ContentDB>> call = service.getServerStores();
        try{
            List<ContentDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<ContentDB> findServerStoreByName(String storeName){
        Call<List<ContentDB>> call = service.findServerStoreByName(storeName);
        try{
            List<ContentDB> content = call.execute().body();
            return content;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<ContentDB> findServerStoreById(String storeId){
        Call<List<ContentDB>> call = service.findServerStoreById(storeId);
        try{
            List<ContentDB> content = call.execute().body();
            return content;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<ContentDB> findServerStoreByLocation(float[] clickedXY) {
        int clickedX = (int) (clickedXY[0] * 1000);
        int clickedY = (int) (clickedXY[1] * 1000);
        Call<List<ContentDB>> call = service.findServerStoreByLocation(clickedX, clickedY);
        try{
            List<ContentDB> content = call.execute().body();
            return content;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    // User
    public List<UserDB> getServerUsers(){
        Call<List<UserDB>> call = service.getServerUsers();
        try{
            List<UserDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<UserDB> findUserById(int id){
        Call<List<UserDB>> call = service.findUserById(id);
        try{
            List<UserDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<UserDB> findUserByNickname(String nickname){
        Call<List<UserDB>> call = service.findUserByNickname(nickname);
        try{
            List<UserDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<UserDB> findUserByEmail(String email){
        Call<List<UserDB>> call = service.findUserByEmail(email);
        try{
            List<UserDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    // Review
    public List<ReviewDB> getServerReviews(){
        Call<List<ReviewDB>> call = service.getServerReviews();
        try{
            List<ReviewDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<ReviewDB> findReviewsByStore(String storeId){
        Call<List<ReviewDB>> call = service.findReviewsByStore(storeId);
        try{
            List<ReviewDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<ReviewDB> findReviewsByUserId(int userId){
        Call<List<ReviewDB>> call = service.findReviewsByUserId(userId);
        try{
            List<ReviewDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }

    public List<ReviewDB> findReviewsByReviewId(int reviewId){
        Call<List<ReviewDB>> call = service.findReviewsByReviewId(reviewId);
        try{
            List<ReviewDB> contents = call.execute().body();
            return contents;
        }
        catch (IOException e){
            Log.e("Proxy", e.getMessage());
        }
        return null;
    }
}
