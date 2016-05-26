package clothesup.turningpoint.clothesup;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hanbyeol on 2016-05-25.
 */
public class ApplicationController extends Application {

    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    public void buildNetworkService() {
        synchronized(ApplicationController.class) {
            if(networkService == null) {
                Gson gson = new GsonBuilder().create();
                GsonConverterFactory factory = GsonConverterFactory.create(gson);

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://52.196.54.163:1337")
                        .addConverterFactory(factory).build();
                networkService = retrofit.create(NetworkService.class);
            }
        }
    }
}
