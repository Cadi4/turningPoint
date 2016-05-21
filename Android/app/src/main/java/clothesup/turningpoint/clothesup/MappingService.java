package clothesup.turningpoint.clothesup;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bro Jo on 2016-05-06
 */
public interface MappingService {
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
}
