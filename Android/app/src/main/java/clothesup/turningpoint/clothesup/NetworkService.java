package clothesup.turningpoint.clothesup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hanbyeol on 2016-05-25.
 */
public interface NetworkService {
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
}