package clothesup.turningpoint.clothesup.store;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import clothesup.turningpoint.clothesup.R;
import clothesup.turningpoint.clothesup.network.ApplicationController;
import clothesup.turningpoint.clothesup.data.ContentDB;
import clothesup.turningpoint.clothesup.network.MappingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreFragment extends Fragment {
    private ListView storeListView;
    private StoreListViewAdapter storeListViewAdapter;
    private MappingService service;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_store, container, false);
        initDropDownView();
        setNetwork();
        getListFromServer();
        return view;
    }

    private void setNetwork() {
        ApplicationController application = new ApplicationController();
        service = application.buildNetworkService();
    }

    private void initDropDownView() {
        String [] rank = {"별점수", "조회수", "후기수"};
        Spinner spinner_rank = (Spinner) view.findViewById(R.id.spinner_ranking);
        ArrayAdapter<String> RKadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, rank);
        RKadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_rank.setAdapter(RKadapter);
        setRetainInstance(true);    // Retain fragment instance across configuration changes
    }

    private void getListFromServer() {
        Call<List<ContentDB>> thumbnailCall = service.getServerStores();
        thumbnailCall.enqueue(new Callback<List<ContentDB>>() {
            @Override
            public void onResponse(Call<List<ContentDB>> call, Response<List<ContentDB>> response) {
                if (response.isSuccessful()) {
                    List<ContentDB> contentList = response.body();
                    makeListView(contentList);
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드:" + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<ContentDB>> call, Throwable t) {
                Log.e("MyTag", "서버 onFailure 에러내용:" + t.getMessage());
            }
        });
    }

    private void makeListView(final List<ContentDB> contentList) {
        Log.i("makeListView", contentList.get(0).toString());
        Log.i("makeListView", contentList.get(1).toString());
        Log.i("makeListView", contentList.get(2).toString());
        storeListView = (ListView) view.findViewById(R.id.listView_storeItemList);
        storeListViewAdapter = new StoreListViewAdapter(this.getContext(), contentList);
        storeListView.setAdapter(storeListViewAdapter);

        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(getContext(), DetailStoreActivity.class);

                detailIntent.putExtra("image", contentList.get(position).getInfo().getImage());
                detailIntent.putExtra("id", contentList.get(position).getId());
                detailIntent.putExtra("name", contentList.get(position).getName().get(0));

                startActivity(detailIntent);
            }
        });
    }
}