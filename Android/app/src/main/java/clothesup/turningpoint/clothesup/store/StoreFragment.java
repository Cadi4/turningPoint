package clothesup.turningpoint.clothesup.store;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import clothesup.turningpoint.clothesup.R;
import clothesup.turningpoint.clothesup.network.ApplicationController;
import clothesup.turningpoint.clothesup.data.ContentDB;
import clothesup.turningpoint.clothesup.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreFragment extends Fragment implements View.OnClickListener {
    private ListView mListView;
    private StoreListViewAdapter mAdapter;
    private TextView result;
    private Button btn_all;
    private NetworkService service;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_store, container, false);
        initDropDownView();
        setNetWork();
        getListFromServer();
        return view;
    }

    private void setNetWork() {
        ApplicationController application = new ApplicationController();
        application.buildNetworkService();
        service = application.getNetworkService();
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
                if(response.isSuccessful()) {
                    List<ContentDB> contentList = response.body();

                    String show_txt = "";
                    for(ContentDB contentDB : contentList) {
                        show_txt += "\r\n" + contentDB.getId();
                    }
                    makeListView(contentList);
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드:" + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<ContentDB>> call, Throwable t) {
                Log.i("MyTag", "서버 onFailure 에러내용:" + t.getMessage());
            }
        });
    }

    private void makeListView(List<ContentDB> contentList) {
        Log.e("makeListView", contentList.get(0).toString());
        Log.e("makeListView", contentList.get(1).toString());
        Log.e("makeListView", contentList.get(2).toString());
        mListView = (ListView) view.findViewById(R.id.listView_storeItemList);
        mAdapter = new StoreListViewAdapter(this.getContext(), contentList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
    }

    public static class ViewHolder {
        public TextView storeRank;
        public ImageView storeImage;
        public TextView storeName;
        public TextView storeNum;
        public TextView storeHit;
    }
}