package clothesup.turningpoint.clothesup;

import android.util.Log;

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
}
