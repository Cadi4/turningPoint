package clothesup.turningpoint.clothesup.network;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hanbyeol on 2016-05-25.
 */
public class ApplicationController extends Application {

    private MappingService mappingService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MappingService getNetworkService() {
        return mappingService;
    }

    public void buildNetworkService() {
        synchronized(ApplicationController.class) {
            if(mappingService == null) {

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://52.196.54.163:1337")
                        .addConverterFactory(GsonConverterFactory.create()).build();
                mappingService = retrofit.create(MappingService.class);
            }
        }
    }
}
